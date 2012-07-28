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
%>

</body>
</html>