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
    	 resp.getWriter().println(add(name));
    }
	public String add(String name)
	{
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Entity test = new Entity("test");
		
		test.setProperty("name", name);
		
		datastore.put(test);
		
		return "Gae2AndroidServlet OK";
	}
}