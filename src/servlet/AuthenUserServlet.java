package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

@SuppressWarnings("serial")
public class AuthenUserServlet extends HttpServlet {

   private static final Map<String, String> openIdProviders;
    static {
        openIdProviders = new HashMap<String, String>();
        openIdProviders.put("Google", "https://www.google.com/accounts/o8/id");
        openIdProviders.put("Yahoo", "yahoo.com");
        openIdProviders.put("MyOpenId.com", "myopenid.com");
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    
            throws IOException {
    	
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser(); // or req.getUserPrincipal()
    Set<String> attributes = new HashSet();

      //  response.setContentType("text/html");
      //  PrintWriter out = response.getWriter();
    //    JSONObject jsonlogin = new JSONObject();
        

        if (user != null) {
        	
        	JSONObject json = new JSONObject();
        //	String logoutUrl = userService.createLoginURL("/ha-search-screen.html");
        	try {
				json.put("nickname", user.getNickname());
				syncCache.put("nickname", user.getNickname());
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	 for (String providerName : openIdProviders.keySet()) {
                 String loginUrl = userService.createLoginURL("/ha-search-screen.html");
                 try {
 					json.put(providerName,loginUrl);
 				} catch (JSONException e) {
 					// TODO Auto-generated catch block
 					e.printStackTrace();
 				}
             }
        	
        	response.setContentType("application/json");
        	PrintWriter out = response.getWriter();   
            
            
        	out.print(json);
        	out.flush();  	
        /*	PrintWriter out = response.getWriter();
            out.println("Hello <i>" + user.getNickname() + "</i>!");
            out.println("[<a href=\""
                    + userService.createLogoutURL(request.getRequestURI())
                    + "\">sign out</a>]");*/
        } else {
        		JSONObject json = new JSONObject();
            for (String providerName : openIdProviders.keySet()) {
                String loginUrl = userService.createLoginURL("/ha-search-screen.html");
                try {
					json.put(providerName,loginUrl);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            	response.setContentType("application/json");
            	PrintWriter out = response.getWriter();       
                
                
            	out.print(json);
            	out.flush();
        }        

    }
}
