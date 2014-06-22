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
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		resp.setContentType("text/xml; charset=UTF-8");
		resp.setCharacterEncoding("utf-8");

		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

		String str = req.getParameter("search");
		Filter mFilter = null;
		Query query = null;

		if (req.getParameter("kind").equals("n"))
		{
			mFilter = new FilterPredicate("name", FilterOperator.EQUAL, str);
		} 
		else if (req.getParameter("kind").equals("d")) 
		{
			mFilter = new FilterPredicate("date", FilterOperator.EQUAL, str);
		}

		query = new Query("Perfor");
		query.setFilter(mFilter);
		PreparedQuery pq = datastore.prepare(query);

		PrintWriter out = resp.getWriter();
		String outString = "";
		/*
		 * for(Entity result : pq.asIterable()) { perfor += (String)
		 * result.getProperty("pr"); }
		 */

		try {

			// Connection conn = DriverManager.getConnection(url, "testuser",
			// "5555");

			// ResultSet rs = (ResultSet) conn.createStatement().executeQuery(
			// "SELECT guestName, content, entryID FROM guestbook.entries");

			outString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
			outString += "<data>";
			outString += "<result>1</result>\n";
			outString += "<documents>\n";
			//
			for (Entity result : pq.asIterable())
			{
				outString += "<document>\n";
				outString += "<name>" + result.getProperty("name") + "</name>";
				outString += "<date>" + result.getProperty("date") + "</date>";
				outString += "<time>" + result.getProperty("time") + "</time>";
				outString += "<pr>" + result.getProperty("pr") + "</pr>";
				outString += "<lat>" + result.getProperty("latitude") + "</lat>";
				outString += "<lon>" + result.getProperty("longitude") + "</lon>";
				// outString += "<content>"+new
				// String(result.getString("content"))+"</content>";
				// outString += "<id>"+result.getString("entryID")+"</id>";
				outString += "</document>\n";
			}
			outString += "</documents>\n";
			// conn.close();

		} catch (Exception e) {
			System.out.println(e.toString());
			outString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
			outString += "<result>2</result>\n";
			e.printStackTrace();
		}
		outString += "</data>";
		// System.out.println(obj.toString());
		out.println(outString);
		// out.println(gson.toJson(books));
	}
}