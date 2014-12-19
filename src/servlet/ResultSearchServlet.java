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
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.PreparedQuery;

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
		Query qTraining = new Query("Training");
		qTraining.addFilter("lastName", Query.FilterOperator.EQUAL, lastNameParam); 
		qTraining.addFilter("height", Query.FilterOperator.LESS_THAN, maxHeightParam);
		//Récupération du résultat de la requète à l’aide de PreparedQuery 
		PreparedQuery pq = datastore.prepare(qTraining);
		for (Entity Trainingresult : pq.asIterable()) {
			String TitleTraining = (String)Trainingresult.getProperty("title");
			if(TitleTraining.contains(research)){
				int dureeTraining=0;
				Query qExerciceTime = new Query("Exercice");
				qExerciceTime.addFilter("TrainingPlan", Query.FilterOperator.EQUAL, TitleTrainingPlan);
				PreparedQuery pqExerciceTime = datastore.prepare(qExerciceTime);
				for (Entity resultExerciceTime : pqExerciceTime.asIterable()) {
					String dureeExercice = (String)resultExerciceTime.getProperty("duree");
					
					dureeExercice= dureeExercice.substring(0, dureeExercice.length()-3);
					dureeTraining += Integer.parseInt(dureeExercice);
				}
				String dureeTrainingS = dureeTraining + "min";
				
				TrainingPlansInfos += "nextvalue" + TitleTrainingPlan + "nextvalue" + dureeTrainingS + "nexttraining";
				
			}
		}
	}
}