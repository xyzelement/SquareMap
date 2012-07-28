package sqm.json;


import java.util.Collection;

public class Venue {
	public String name;
	public Location location;
	public Collection<Category> categories;
	
	
	public Venue(){};
	public String toString() {
		String cat = "[no cat]";
		if (!categories.isEmpty()) {
			cat =categories.iterator().next().toString();
		}
		return name+" "+location+":" + cat;
	}
}
