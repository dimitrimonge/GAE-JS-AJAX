package servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.memcache.ErrorHandlers;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;




public class ResultDetailServlet extends HttpServlet {
	private DatastoreService datastore;
	
	public ResultDetailServlet() {
	        super();
			datastore = DatastoreServiceFactory.getDatastoreService();
	    }
	    
	   @Override
	   public void init() throws ServletException {
		   super.init();

	   }

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			JSONArray arrayObj = new JSONArray();
			String descript; // Définir l'objet à stocker
			String titrePlan=request.getParameter("titre");
			//String title="Training plan"; // Définir la clé de la valeur à stocker
			
			// Méthode de cache synchrone
			MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService();
			syncCache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));
			descript = (String ) syncCache.get(titrePlan); // Lecture depuis le cache
			if (descript == null) {
				
				//creation d'un filtre permetant de reperer le nom de notre training plan
				Filter NameFilter =
						  new FilterPredicate("inputTitle",
						                      FilterOperator.EQUAL,
						                      titrePlan);

				// on recupere les lignes du datasotre correspondant a notre plan d'exercice 
				Query q = new Query("Exercice").setFilter(NameFilter);
				
				 
				// Récupération du résultat de la requète à l’aide de PreparedQuery 
				PreparedQuery pq = datastore.prepare(q);
				
				for (Entity result : pq.asIterable()) 
				{// parcours les lignes des resultats notre filtre 
					
					  JSONObject obj = new JSONObject();
					 
					  
					  
					  String inputTitle =(String) result.getProperty("inputTitle");
					  String inputDescription =(String) result.getProperty("inputDescription");
					  String domain =(String) result.getProperty("domain");
					  String timeID =(String) result.getProperty("timeID");
					  String exercicesTitle = (String) result.getProperty("exercicesTitle");
					  String exercicesDescr = (String) result.getProperty("exercicesDescr");
					  String exercicesTime =(String) result.getProperty("exercicesTime");
					  
					  
					  
					  try 
					  {
						obj.put("inputTitle",inputTitle);
						obj.put("inputDescription",inputDescription);
						obj.put("timeID",timeID);
						obj.put("title",exercicesTitle);
						
						obj.put("title",exercicesTitle);
						obj.put("descr",exercicesDescr);
						obj.put("time",exercicesTime);
						arrayObj.put(obj);
					  }
					  catch (JSONException e) 
					  {
						// TODO Auto-generated catch block
						e.printStackTrace();
					  }
					  
					}
		
			}
			
			JSONObject json = new JSONObject();
		    try {
				json.put("arrayObj", arrayObj);
				json.write(response.getWriter());
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		}
		

}

