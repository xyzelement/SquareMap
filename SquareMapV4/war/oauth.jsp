<%@ page import="sqm.oauth_connector" %>
<%@ page import="sqm.UrlFetcher" %>

<%
   response.setHeader( "Pragma", "no-cache" );
   response.setHeader( "Cache-Control", "no-cache" );
   response.setDateHeader( "Expires", 0 );
%>


<% 
if (request.getParameter("logout")!=null) {
	session.removeAttribute("token");
	response.sendRedirect("index.jsp");
} else if  (request.getParameter("code") == null) { 
	// Here means we have not yet requested oauth, so request it.
	String auth_url = oauth_connector.getLoginUrl();
    response.sendRedirect(auth_url);
} else { 
	// Here means we are being called-back with the oauth code

	String code=request.getParameter("code");
	String token_url = oauth_connector.getTokenUrl(code);

	String doc = UrlFetcher.fetch(token_url);

	String token = oauth_connector.parseToken(doc);

	session.setAttribute("token", token);
	response.sendRedirect("index.jsp");
} 
%>