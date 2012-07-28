package sqm;

import java.util.List;

import sqm.json.ItemOut;
import sqm.json.ItemsOut;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;

public class DbStuff {
	public static void save(ItemOut item) {
	        String uid = "ed";
	        Key uidKey = KeyFactory.createKey("Visits", uid);
	        	        
	        Entity visit = new Entity("Visit", uidKey);
	        visit.setProperty("user", uid);
	        visit.setProperty("date", item.date);
	        visit.setProperty("name", item.name);
	        visit.setProperty("lat", item.lat);
	        visit.setProperty("lng", item.lng);
	        visit.setProperty("icon", item.icon);
	        visit.setProperty("createdAt", item.createdAt);
	        
	        DatastoreService datastore =
	                DatastoreServiceFactory.getDatastoreService();
	        datastore.put(visit);
			System.out.println("Saved " + item.name);
	}

	public static void delete() {
	    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	    Key key = KeyFactory.createKey("Visits", "ed");
	    datastore.delete(key);
	}
	
	public static ItemOut getOldest(boolean newest) {
	    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	    Key key = KeyFactory.createKey("Visits", "ed");

	    
	    Query query = new Query("Visit", key).addSort("createdAt", 
	    		newest ? Query.SortDirection.DESCENDING : Query.SortDirection.ASCENDING);
	    
	    List<Entity> vis = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(1));
	    if (vis.isEmpty()) {
	    	System.out.println("Couldnt load anything");
	    	return null;
	    }	
	    
	    Entity ent = vis.get(0);
    	ItemOut e = new ItemOut(
    			(String)ent.getProperty("name"), 
    			(String)ent.getProperty("date"), 
    			((Long)ent.getProperty("createdAt")).longValue(), 
    			((Double)ent.getProperty("lat")).doubleValue(),
    			((Double)ent.getProperty("lng")).doubleValue(),
    			(String)ent.getProperty("icon"));
    	
    	return e;
	}
	
	public static ItemsOut load(){
	    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	    Key key = KeyFactory.createKey("Visits", "ed");

	    Query query = new Query("Visit", key).addSort("createdAt", Query.SortDirection.DESCENDING);
	    
	    //EMTODO: not in love with the hardcoded limit
	    List<Entity> vis = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(50000));
	    if (vis.isEmpty()) {
	    	System.out.println("Couldnt load anything");
	    	return null;
	    }
	    
	    
	    ItemsOut io = new ItemsOut();
	    for (Entity ent : vis) {	    	 
	    	System.out.println("Entity Loaded!!!!! " + ent.getProperty("createdAt") + " " + ent.getProperty("name") + ent.getProperty("date"));
	    	ItemOut e = new ItemOut(
	    			(String)ent.getProperty("name"), 
	    			(String)ent.getProperty("date"), 
	    			((Long)ent.getProperty("createdAt")).longValue(), 
	    			((Double)ent.getProperty("lat")).doubleValue(),
	    			((Double)ent.getProperty("lng")).doubleValue(),
	    			(String)ent.getProperty("icon"));
	    	io.items.add(e);
	    }
		return io;
	}	
	
}
