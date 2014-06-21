package buskingserver;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

@SuppressWarnings("serial")
public class Gae2AndroidServlet extends HttpServlet {
	
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
    	 String name = req.getParameter("name");
    	 String pr = req.getParameter("pr");
    	 String date = req.getParameter("date");
    	 String latitude = req.getParameter("latitude");
    	 String longitude = req.getParameter("longitude");
    	 
    	 resp.getWriter().println(add(name, pr, date, latitude, longitude));
    }
	public String add(String name, String pr, String date, String latitude, String longitude)
	{
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Entity place = new Entity("Place");
		
		place.setProperty("name", name);
		place.setProperty("pr", pr);
		place.setProperty("date", date);
		place.setProperty("latitude", latitude);
		place.setProperty("longitude", longitude);
		
		datastore.put(place);
		
		return "Gae2AndroidServlet OK";
	}
}