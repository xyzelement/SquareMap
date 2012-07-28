<%@page import="sqm.DbStuff"%>

<%@page import="sqm.json.ItemsOut"%>
<%@page import="sqm.json.ItemOut"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.Iterator"%>

<%
   response.setHeader( "Pragma", "no-cache" );
   response.setHeader( "Cache-Control", "no-cache" );
   response.setDateHeader( "Expires", 0 );
%>

<html>
<body>

<%
	String token = (String)session.getAttribute("token");
	if (token != null) { %>
		<a href="oauth.jsp?logout">click here to remove it</a>
		<a href="history_downloader.jsp?cycle=0">click here to download hist</a>
	<% } else { %>
		Your token is not set,
		<a href="oauth.jsp">click here to set it</a>
	<% 	}
	
	ItemsOut io = DbStuff.load();
	if (io != null) {
		pageContext.getOut().append(io.toHtml());
		
		ItemOut oldest = DbStuff.getOldest(false);
		ItemOut newest = DbStuff.getOldest(true);
		
		pageContext.getOut().append("<BR><BR>NewestItem:" + newest);
		pageContext.getOut().append("<BR><BR>OldestItem:" + oldest);
		%>
			<a href="history_downloader.jsp?older_than=<%= oldest.createdAt %>">click here to download hist</a>
		<%
	} else {
		pageContext.getOut().append("<BR><BR>Nothing to load");
	}
%>

</body>
</html>