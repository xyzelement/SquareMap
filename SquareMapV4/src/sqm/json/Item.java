package sqm.json;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Item {
	public Venue venue;
	public long createdAt;

	
	public Item(){};
	
	
	public String formatedDate() {
		//EMTODO: clean up the formatter
		Date d = new Date(createdAt*1000);
		Format formater = new SimpleDateFormat("EEE, MMM d, yyyy");
		return formater.format(d);		
	}
	
	
	public String toString() {	
		return venue + " " + formatedDate();
	}
	
	public String toHtml(){
		return "<li>"+formatedDate() + " * " + venue.toHtml() + "</li>";
	}
}
