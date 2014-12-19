package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;


public class ResultSearchServlet extends HttpServlet {
	private DatastoreService datastore;
       
    public ResultSearchServlet() {
        super();
    }
    
   @Override
   public void init() throws ServletException {
	   super.init();
	   //Create a connection to the datastore ONETIME at the init servlet process
		datastore = DatastoreServiceFactory.getDatastoreService();
   }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		JSONArray arrayJson = new JSONArray();
		String research =request.getParameter("search");
		//creation d'un filtre permetant de reperer le nom de notre training plan
		Filter NameFilter =
				  new FilterPredicate("inputTitle",
				                      FilterOperator.EQUAL,
				                      research);

		
		// Utilisation Query afin de rassembler les éléments a appeler/filter 
				Query qTraining = new Query("Trainning").setFilter(NameFilter);
				//Récupération du résultat de la requète à l’aide de PreparedQuery 
				PreparedQuery pq = datastore.prepare(qTraining);
		
		for (Entity result : pq.asIterable()) 
		{// parcours les lignes des resultats notre filtre 
			
			  JSONObject json = new JSONObject();
			 
			  
			  
			  String inputTitle =(String) result.getProperty("inputTitle");
			  String timeID =(String) result.getProperty("timeID");
			  
			  
			  
			  try 
			  {
				json.put("inputTitle",inputTitle);
				json.put("timeID",timeID);
				arrayJson.put(json);
			  }
			  catch (JSONException e) 
			  {
				// TODO Auto-generated catch block
				e.printStackTrace();
			  }
				response.setContentType("application/json");
				PrintWriter out = response.getWriter();

				out.print(arrayJson);
				out.flush();

			}
		}
	}