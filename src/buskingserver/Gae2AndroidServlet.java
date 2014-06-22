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
    	 String time = req.getParameter("time");
    	 String latitude = req.getParameter("latitude");
    	 String longitude = req.getParameter("longitude");
    	 
    	 resp.getWriter().println(add(name, pr, date, time, latitude, longitude));
    }
	public String add(String name, String pr, String date, String time, String latitude, String longitude)
	{
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Entity perfor = new Entity("Perfor");
		
		perfor.setProperty("name", name);
		perfor.setProperty("pr", pr);
		perfor.setProperty("date", date);
		perfor.setProperty("time", time);
		perfor.setProperty("latitude", latitude);
		perfor.setProperty("longitude", longitude);
		
		datastore.put(perfor);
		
		return "DataStore OK";
	}
}