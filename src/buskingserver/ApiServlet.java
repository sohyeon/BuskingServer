package buskingserver;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

@SuppressWarnings("serial")
public class ApiServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		resp.setContentType("text/plain; charset=UTF-8");
		resp.setCharacterEncoding("utf-8");
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		String str = req.getParameter("search");
		Filter mFilter = new FilterPredicate("name", FilterOperator.EQUAL, str);
		Query query = new Query("Perfor");
		query.setFilter(mFilter);
		PreparedQuery pq = datastore.prepare(query);
		
		String perfor = "";
		for(Entity result : pq.asIterable()) {
			perfor += (String) result.getProperty("pr");
		}
		
		PrintWriter out = resp.getWriter();
		out.println(perfor);
	}
}