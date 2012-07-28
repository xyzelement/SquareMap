package sqm.json;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.google.gson.Gson;

public class ItemsOut {
	public Collection<ItemOut> items;
	public ItemsOut() {}
	public ItemsOut(Collection<Item> items) {
		Iterator<Item> it = items.iterator();
		this.items = new ArrayList<ItemOut>();
		System.out.println(" - Here - ");
		while (it.hasNext()) {
			
			Item i = it.next();
			System.out.println("     - Here - " + i.venue.name + i);
			
			if (i.venue == null || i.venue.name == null) {
				System.out.println("meh");
			}
			
			this.items.add(new ItemOut(i.venue.name, 
					i.formatedDate(), i.venue.location.lat, 
					i.venue.location.lng, 
					i.venue.categories.isEmpty()
						? null
						: i.venue.categories.iterator().next().icon.getUrl()
				));
			
			System.out.println("added");
		}
		System.out.println("dones");
	}
	
	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
}
