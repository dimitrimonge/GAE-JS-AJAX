package servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.logging.Level;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.memcache.ErrorHandlers;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

public class AjoutPlanServlet extends HttpServlet {
	private DatastoreService datastore;
       
    public AjoutPlanServlet() {
        super();
    }
    
   @Override
   public void init() throws ServletException {
	   super.init();
	   //Create a connection to the datastore ONETIME at the init servlet process
		datastore = DatastoreServiceFactory.getDatastoreService();
   }
/*	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Entity text = new Entity("text");
		text.setProperty("Title","welcome");
		text.setProperty("descript", "ceci est un message daccueil ");
		datastore.put(text);
		
	}*/
public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
{
		
	Entity text = new Entity("exercice");
	
	text.setProperty("titre","Personal Trainer");
	text.setProperty("description", "texte d'accueil");
	text.setProperty("domaine", "texte d'accueil");
	 
	//for (int i)
	
	String test =request.getParameter("#titleDescription");
//	String youh = $("#titleDescription").val(); 
	
	datastore.put(text);
		
	
	response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
    response.setCharacterEncoding("UTF-8"); 
   // response.getWriter().write(descript);       // Write response body.
		
}
	
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	}
	
	}
