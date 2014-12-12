package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

public class InitServlet extends HttpServlet {
	private DatastoreService datastore;
       
    public InitServlet() {
        super();
    }
    
   @Override
   public void init() throws ServletException {
	   super.init();
	   //Create a connection to the datastore ONETIME at the init servlet process
		datastore = DatastoreServiceFactory.getDatastoreService();
   }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Entity text = new Entity("text");
		text.setProperty("Title","Personal Trainer");
		text.setProperty("descript", "texte d'accueil");
		datastore.put(text);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		}
	
	}