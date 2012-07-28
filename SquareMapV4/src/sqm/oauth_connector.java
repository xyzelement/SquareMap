package sqm;

import sqm.json.History;

import com.google.gson.Gson;


public class oauth_connector {
	
	//oauth placeholder

	// Prod App
	private static String eds_token = "";
	private static String client_id     = "";
	private static String client_secret = "";
	private static String redirect_url  = "";
	
	public static String getLoginUrl() {
		return "https://foursquare.com/oauth2/authenticate"
				+ "?client_id="+client_id
				+ "&response_type=code"
				+ "&redirect_uri=" + redirect_url;
	}
	                     
	public static String getTokenUrl(String code) {
		return "https://foursquare.com/oauth2/access_token"
			+"?client_id="+client_id
			+"&client_secret="+client_secret
			+"&grant_type=authorization_code"
			+"&redirect_uri=" + redirect_url
			+"&code="+code;		
	}

	
	public static String parseToken(String token){		
	    Gson gson = new Gson();
	    AccessToken a = gson.fromJson(token, AccessToken.class);
	    System.out.println(gson.toJson(a) + "<- " );
	    return a.access_token;
	}
	
}
