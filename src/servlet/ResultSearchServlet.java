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
	
		String keywords =request.getParameter("research");
		String[] keyWordsArray = keywords.split("_");
		
		String TrainingPlansInfos ="";
		String ExerciceInfos="";
		
		Query qTraining = new Query("TrainingPlan");
		PreparedQuery pqTraining = datastore.prepare(qTraining);
		for (Entity resultTraining : pqTraining.asIterable()) 
		{
			String TitleTrainingPlan = (String)resultTraining.getProperty("title");
			
			if(TitreContientKeyWords(keyWordsArray,TitleTrainingPlan))
			{
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
		
		
		
		
		Query qExercice = new Query("Exercice");
		PreparedQuery pqExercice = datastore.prepare(qExercice);
		for (Entity resultExercice : pqExercice.asIterable()) 
		{
			String TitleExercice = (String)resultExercice.getProperty("title");
			String dureeExercice = (String)resultExercice.getProperty("duree");
			
			if(TitreContientKeyWords(keyWordsArray,TitleExercice))
				ExerciceInfos += "nextvalue" + TitleExercice + "nextvalue" + dureeExercice + "nexttraining";
			
			//Format the answer
			response.setContentType("application/json");
			JSONObject json;
			json = new JSONObject();
			
			json.put("training", TrainingPlansInfos);
			json.put("exercice", ExerciceInfos);
			
			//Send the Json object to the web browser
        	PrintWriter out = response.getWriter();   
            
            
        	out.print(json);
        	out.flush();
		}
	}
		public boolean TitreContientKeyWords(String[] keyWordsArray,String Titre){
			
			for(String KeyWord:keyWordsArray) {
			   if(Titre.contains(KeyWord))
				   return true;
			}
			
			
			
			return false;
		}
}