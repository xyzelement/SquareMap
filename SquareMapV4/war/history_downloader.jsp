<%@ page import="sqm.oauth_connector"%>
<%@ page import="sqm.json.History"%>
<%@ page import="sqm.json.Item"%>
<%@ page import="sqm.json.ItemOut"%>
<%@ page import="sqm.json.ItemsOut"%>
<%@ page import="java.util.Collection"%>
<%@ page import="java.util.Iterator"%>

<%
	//Collection<Item> list 
	//  = (Collection<Item>)session.getAttribute("list");


	//int cycle = Integer.parseInt(request.getParameter("cycle"));
	String token = (String)session.getAttribute("token");

	History h;
	if(request.getParameter("older_than") !=null) {
		System.out.println(request.getParameter("older_than"));
		int olderThan = Integer.parseInt(request.getParameter("older_than"));
		pageContext.getOut().append("Looking for stuff older than: " + olderThan);
		
		//EMTODO: olderThan is completelybackwards in 4sq so this is busted.
		// We need to do this:
		// for the original history backfill: do the cycle offset stuff. Once it's in the
		// db, assume that everything historical is there.
		// for update (ie puling in new stuff) do the same thing (offset 1), keeping track
		// of latest known item. Once the update crosses what we already know, stop updating.
		
		h = History.getHistory(token, olderThan);
	} else {
		h = History.getHistory(token, 0/*cycle*/);
	}
	
	Collection<Item> list = h.getItems();
	
	pageContext.getOut().append("Downloaded: " 
		+ list.size() + " of " + h.response.checkins.count);
	
		
	ItemsOut its = new ItemsOut(list);
	pageContext.getOut().append(its.toHtml());
	its.save();

	%>	
	
