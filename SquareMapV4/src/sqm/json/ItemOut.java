package sqm.json;

import com.google.gson.Gson;

public class ItemOut {
	public String name;
	public String date;
	public long createdAt;
	public double lat;
	public double lng;
	public String icon;
	
	public ItemOut() {}
	public ItemOut(String name, String date, long createdAt, 
			double lat, double lng, String icon) {
		this.name = name;
		this.date = date;
		this.createdAt = createdAt;
		this.lat  = lat;
		this.lng  = lng;
		this.icon = icon;
	}
	
	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
	
	public String toHtml() {
		return "<li><img src='" + icon + "'>" + date + " " + name + "</li>"; 
	}
}
