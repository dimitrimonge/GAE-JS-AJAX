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
	
		String research =request.getParameter("research");
		// Utilisation Query afin de rassembler les éléments a appeler/filter 
		Query qTraining = new Query("Trainning");
		//Récupération du résultat de la requète à l’aide de PreparedQuery 
		PreparedQuery pq = datastore.prepare(qTraining);
		
		String TrainingPlansInfos ="";
		String ExerciceInfos="";
		
		for (Entity Trainingresult : pq.asIterable()) {
			String TitleTraining = (String)Trainingresult.getProperty("inputTitle");
			//mise en place des filtres
			Filter Titlefilter = new FilterPredicate ("InputTitle", Query.FilterOperator.EQUAL, TitleTraining);
			
			if(TitleTraining.contains(research)){
				int dureeTraining=0;
				Query qExerciceTime = new Query("Exercice");
				qExerciceTime.setFilter(Titlefilter);
				PreparedQuery pqExerciceTime = datastore.prepare(qExerciceTime);
				for (Entity resultExerciceTime : pqExerciceTime.asIterable()) {
					String dureeExercice = (String)resultExerciceTime.getProperty("exercicesTime");
					
					dureeExercice= dureeExercice.substring(0, dureeExercice.length()-3);
					dureeTraining += Integer.parseInt(dureeExercice);
				}
				String dureeTrainingS = dureeTraining + "min";
				
				TrainingPlansInfos += "nextvalue" + TitleTraining + "nextvalue" + dureeTrainingS + "nexttraining";
				
			}
		}
		Query qExercice = new Query("Exercice");
		PreparedQuery pqExercice = datastore.prepare(qExercice);
		for (Entity resultExercice : pqExercice.asIterable()) 
		{
			String TitleExercice = (String)resultExercice.getProperty("exercicetitle");
			String dureeExercice = (String)resultExercice.getProperty("exercicesTime");
			
			if(TitleExercice.contains(research)){
				ExerciceInfos += "nextvalue" + TitleExercice + "nextvalue" + dureeExercice + "nexttraining";
			}
			
		}
		//Format the answer
		JSONObject json= new JSONObject();
		try {
			json.put("training", TrainingPlansInfos);
			json.put("exercice", ExerciceInfos);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();

		out.print(json);
		out.flush();
	}
}