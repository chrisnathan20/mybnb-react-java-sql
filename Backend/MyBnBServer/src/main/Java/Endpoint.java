package main.Java;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Random;
import org.json.*;

public class Endpoint implements HttpHandler {

    public MySQLdao dao;
    
    private Hashtable<Integer, String> sessionUsername;

    public Endpoint() throws SQLException {
        this.dao = new MySQLdao();
        this.sessionUsername =  new Hashtable<Integer, String>();
    }

    public void handle(HttpExchange r) {
        try {
            switch (r.getRequestMethod()) {
                case "GET":
                    this.handleGet(r);
                    break;
                case "POST":
                    this.handlePost(r);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleGet(HttpExchange r) throws IOException, JSONException {
    	String path = r.getRequestURI().toString();
    	String method = r.getRequestMethod();

        // Confirmation to terminal/console if http request is received by server
        System.out.println(method + " method for the path " + path);
        
        if (path.contains("/mybnb/getprofile")){
            this.handleLogin(r);
        }
        else {
            r.sendResponseHeaders(500, -1);
            return;
        }
    }
    
    public void handlePost(HttpExchange r) throws IOException, JSONException {
    	String path = r.getRequestURI().toString();
    	String method = r.getRequestMethod();

        // Confirmation to terminal/console if http request is received by server
        System.out.println(method + " method for the path " + path);
        
        if (path.contains("/mybnb/register")){
            this.handleLogin(r);
        }
        else if (path.contains("/mybnb/login")){
            this.handleLogin(r);
        }
        else {
            r.sendResponseHeaders(500, -1);
            return;
        }
    }
    
    public void handleLogin(HttpExchange r) throws IOException {
    	String body = Utils.convert(r.getRequestBody());
    	
    	try {
            JSONObject deserialized = new JSONObject(body);
            String username, password;

            if (deserialized.has("username") && deserialized.has("password")) {
                username = deserialized.getString("username");
                password = deserialized.getString("password");
            } else {
                r.sendResponseHeaders(400, -1);
                return;
            }

            try {
                if(this.dao.authenticateLogin(username, password)) {
                	r.sendResponseHeaders(200, -1);	
                	Random rand = new Random();
                	int session = rand.nextInt();
                	
                	while(sessionUsername.containsKey(session)) {
                		session = rand.nextInt();
                	}
                	
                	sessionUsername.put(session, username);
                	
                	System.out.println("Login Successful for " + sessionUsername.get(session) + " with Session Number " + session);
                }
                else {
                	r.sendResponseHeaders(400, -1);
                	System.out.println("Login Failed, size of sessionUsername remains at " + sessionUsername.size());
                }
            } catch (Exception e) {
                r.sendResponseHeaders(500, -1);
                e.printStackTrace();
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            r.sendResponseHeaders(500, -1);
        }
    }
}
