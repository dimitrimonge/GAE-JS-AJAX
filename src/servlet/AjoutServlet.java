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
		String timeID=request.getParameter("timeID");
		
		
		//int exercicesTabTitle[]={0,1,2};
		
		String exercicesTabTitle[]=request.getParameterValues("exercicesTabTitle[]");
		String exercicesTabDescr[]=request.getParameterValues("exercicesTabDescr[]");
		String exercicesTabTime[]=request.getParameterValues("exercicesTabTime[]");
		
	
		/*------------------- FIN recup parametre passé depusi le get ----------------*/
		
		//int taille=exercicesTabTitle.length;
		
		
		
		
		for(int i = 0; i<exercicesTabDescr.length;i++)
		{
			
			Entity text = new Entity("Exercice");
			text.setProperty("inputTitle",inputTitle);
			text.setProperty("inputDescription",inputDescription);
			text.setProperty("domain",domain);
			text.setProperty("timeID",timeID);
			text.setProperty("exercicesTitle",exercicesTabTitle[i]);
			text.setProperty("exercicesDescr",exercicesTabDescr[i]);
			text.setProperty("exercicesTime",exercicesTabTime[i]);
			
			// entrée dans le datastore 
			
			datastore.put(text);
			
		}
		response.equals(1);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		}
	
	}