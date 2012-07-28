package sqm.json;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import sqm.DbStuff;

import com.google.gson.Gson;



public class ItemsOut {
	public Collection<ItemOut> items;
	public ItemsOut() {
		this.items = new ArrayList<ItemOut>();		
	}
	
	public ItemsOut(Collection<Item> items) {
		Iterator<Item> it = items.iterator();
		this.items = new ArrayList<ItemOut>();
		System.out.println(" - Here - ");
		while (it.hasNext()) {
			
			Item i = it.next();
			
			if (i.venue == null || i.venue.name == null) {
				System.out.println("meh");
				continue;
			}
		
			
			System.out.println("     - Here - " + i.venue.name + i);
			
			
			this.items.add(new ItemOut(i.venue.name, 
					i.formatedDate(), 
					i.createdAt,
					i.venue.location.lat, 
					i.venue.location.lng, 
					i.venue.categories.isEmpty()
						? null
						: i.venue.categories.iterator().next().icon.getUrl()
				));
			
			System.out.println("added");
		}
		System.out.println("dones");
	}
	
	
	public void save() {
		for (ItemOut item : items){
			DbStuff.save(item);
		}
	}
	
	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
	
	public String toHtml() {
		String out = new String("<ol>");
		for (ItemOut item : items){
			out += item.toHtml();
		}
		return out+"</ol>";
	}
	
}
