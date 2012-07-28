package sqm.json;

import java.util.Collection;

public class Checkins {
	public int count;
	public Collection<Item> items;
	public Checkins() {}
	public String toString() {
		return "Total: " + count + ".. Downloaded: " + items.toString();
		
	}
}
