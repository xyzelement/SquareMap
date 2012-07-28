<%@ page import="sqm.oauth_connector"%>
<%@ page import="sqm.json.History"%>
<%@ page import="sqm.json.Item"%>
<%@ page import="sqm.json.ItemOut"%>
<%@ page import="sqm.json.ItemsOut"%>
<%@ page import="java.util.Collection"%>
<%@ page import="java.util.Iterator"%>

<%
	Collection<Item> list 
	  = (Collection<Item>)session.getAttribute("list");


	int cycle = Integer.parseInt(request.getParameter("cycle"));
	String token = (String)session.getAttribute("token");

	History h = History.getHistory(token, cycle);
	if (cycle == 0) {
		list = h.getItems();
	} else {
		list.addAll(h.getItems());		
	}
	session.setAttribute("list", list);
	
	pageContext.getOut().append("Downloaded: " 
		+ list.size() + " of " + h.response.checkins.count);
	
	if (list.size() < h.response.checkins.count) {
		String url = "history_downloader.jsp?cycle="+(cycle+1);
		//response.sendRedirect(url);
		%>
		<script language="javascript" type="text/javascript">
			window.setTimeout('window.location="<%= url %>"; ',1000);
		</script>
		<%
	} else {
		
		System.out.println("xxxxxxxxx");
		ItemsOut its = new ItemsOut(list);
		System.out.println("yyyyy");
		//pageContext.getOut().append(its.toString());
		System.out.println("zzzz");
	}
	%>	
	
