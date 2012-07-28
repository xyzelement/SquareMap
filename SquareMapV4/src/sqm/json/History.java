package sqm.json;

import java.util.Collection;
import java.util.Iterator;

import sqm.UrlFetcher;

import com.google.gson.Gson;


public class History {
	public Response response;
	public History() {}
	public String toString() {
		return response.toString();
	}
	
	public Collection<Item> getItems() {
		return response.checkins.items;	
	}
	
	public String itemsToHtml() {
		String out = "<UL>";
		int cnt = 0;
		Iterator<Item> it = getItems().iterator();
		while (it.hasNext()) {
			++cnt;
			Item i = it.next();
			out += "<LI>"+cnt+" "+i.toString()+"</LI>";
		}
		return out + "</UL>";
	}
	
	public static History getHistory(String token, long olderThan) {
		
		
		String url = "https://api.foursquare.com/v2/users/self/checkins?oauth_token="+token+"&v=20120601&limit=100";
		if (olderThan != 0) {
			url += "&beforeTimestamp=" + olderThan;
		}
		System.out.println("Trying to access:" +url);
		String json = UrlFetcher.fetch(url);
			
		Gson gson = new Gson();
		History h = gson.fromJson(json, History.class);
		return h;				
	}

}