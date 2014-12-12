package servlet.welcome;

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

public class WelcomeServlet extends HttpServlet {
	private DatastoreService datastore;
       
    public WelcomeServlet() {
        super();
		datastore = DatastoreServiceFactory.getDatastoreService();
    }
    
   @Override
   public void init() throws ServletException {
	   super.init();

   }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title="Personal Trainer"; // Définir la clé de la valeur à stocker
		String descript; // Définir l'objet à stocker
		// Méthode de cache synchrone
		MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService();
		syncCache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));
		descript = (String ) syncCache.get(title); // Lecture depuis le cache
		if (descript == null) {
			//Utilisation Query afin de rassembler  les elements a appeler /filter
			Query q = new Query("text");
			// Récupération du résultat de la requète à l’aide de PreparedQuery 
			PreparedQuery pq = datastore.prepare(q);
			
			Entity result= pq.asSingleEntity();
			
			title = (String) result.getProperty("Title");
			descript = (String) result.getProperty("descript");
			
		// récupération de la valeur et exécution de son code ….
		syncCache.put(title, descript); // Mise à jour du cache
		}
			
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(descript);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		}
	
	}

