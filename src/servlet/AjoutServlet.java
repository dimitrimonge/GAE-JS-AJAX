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

public class AjoutServlet extends HttpServlet {
	private DatastoreService datastore;
       
    public AjoutServlet() {
        super();
    }
    
   @Override
   public void init() throws ServletException {
	   super.init();
	   //Create a connection to the datastore ONETIME at the init servlet process
		datastore = DatastoreServiceFactory.getDatastoreService();
   }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		/*-------------------recup parametre passé depusi le get ----------------*/
		String inputTitle=request.getParameter("inputTitle");
		String inputDescription=request.getParameter("inputDescription");
		String domain=request.getParameter("domain");
		String exercicesAdded=request.getParameter("exercicesAdded");
		String timeID=request.getParameter("timeID");
		
	
		/*------------------- FIN recup parametre passé depusi le get ----------------*/
		Entity text = new Entity("Exercice");
		text.setProperty("inputTitle",inputTitle);
		text.setProperty("inputDescription",inputDescription);
		text.setProperty("domain",domain);
		text.setProperty("timeID",timeID);
		text.setProperty("exercicesAdded",exercicesAdded);
		
		// entrée dans le datastore 
		datastore.put(text);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		}
	
	}