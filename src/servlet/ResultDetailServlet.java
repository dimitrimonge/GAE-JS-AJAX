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

import org.json.simple.JSONObject;

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
				 String title = "";
				// Récupération du résultat de la requète à l’aide de PreparedQuery 
				PreparedQuery pq = datastore.prepare(q);
				int id = 0;
				for (Entity result : pq.asIterable()) {// parcours les lignes des resultats notre filtre 
					  id=id+1;
					  String exercicesTitle = (String) result.getProperty("exercicesTitle");
					  String exercicesDescr = (String) result.getProperty("exercicesDescr");
					//  int exercicesTime = (int) result.getProperty("exercicesTime");
					  
					 
					 
					  title = title +"/"+ exercicesTitle;
					  
					}
				
				
				title = title + "*o";		// c'est moche mais pas moyen de creer un tableau ??
				//response.getWriter().write(title);
				//request.setAttribute("title", title);

				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(title);
			}
				
				//response.setContentType("text/plain");
				//response.setCharacterEncoding("UTF-8");
				//response.getWriter().write(descript);
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		}
		

}

