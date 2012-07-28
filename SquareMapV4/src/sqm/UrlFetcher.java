package sqm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class UrlFetcher {

	public static String fetch(String page){
		String output="";
        try {
            URL url = new URL(page);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                output+=line;
            }
            reader.close();
        } catch (IOException e) {
            return "Error" + e.getLocalizedMessage();
        }		
        
        System.out.println("----------------");
        System.out.println(output);
        
		return output;
	}
}
