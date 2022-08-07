package main.Java;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
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
        
        if (path.contains("/mybnb/getviewlisting")){
            this.handleGetViewListings(r);
        }
        else {
            r.sendResponseHeaders(500, -1);
            return;
        }

    }
    
    public void handleGetListings(HttpExchange r) throws IOException {
    	String body = Utils.convert(r.getRequestBody());
    	
    	try {
            JSONObject deserialized = new JSONObject(body);
            String sortby, address, city, country, postalcode;
            Double latitude, longitude, minprice, maxprice;
            int distance;

            if (deserialized.has("sortby") && deserialized.has("latitude") && deserialized.has("longitude")
            	&& deserialized.has("distance") && deserialized.has("minprice") && deserialized.has("maxprice")
            	&& deserialized.has("address") && deserialized.has("city") && deserialized.has("country") && deserialized.has("postalcode")) {
            	
                sortby = deserialized.getString("sortby");
                address = deserialized.getString("address");
                city = deserialized.getString("city");
                country = deserialized.getString("country");
                postalcode = deserialized.getString("postalcode");
                
                latitude = deserialized.getDouble("latitude");
                longitude = deserialized.getDouble("longitude");
                minprice = deserialized.getDouble("minprice");
                maxprice = deserialized.getDouble("maxprice");
                
                distance = deserialized.getInt("distance");
                
                
            } 
            else {
            	System.out.println("fails here because " + deserialized.has("longitude"));
                r.sendResponseHeaders(400, -1);
                return;
            }

            try {
            	String responseString = this.dao.getListings(sortby, address, city, country, postalcode, latitude, longitude, minprice, maxprice, distance);
            	r.sendResponseHeaders(200, responseString.length());	
                OutputStream os = r.getResponseBody();
                os.write(responseString.getBytes());
                os.close();            
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
    
    public void handleGetViewListings(HttpExchange r) throws IOException {
    	String path = r.getRequestURI().toString();
    	String[] arrOfStr = path.split("&");
    	System.out.println(path);
        System.out.println(arrOfStr[1]);
        try {
            String response = this.dao.getViewListings(Integer.parseInt(arrOfStr[1]));
        	r.sendResponseHeaders(200, response.length());	
            OutputStream os = r.getResponseBody();
            os.write(response.getBytes());
            os.close();    
        } catch (Exception e) {
            r.sendResponseHeaders(500, -1);
            e.printStackTrace();
            return;
        }
    }
    
    public void handlePost(HttpExchange r) throws IOException, JSONException {
    	String path = r.getRequestURI().toString();
    	String method = r.getRequestMethod();

        // Confirmation to terminal/console if http request is received by server
        System.out.println(method + " method for the path " + path);
        
        if (path.contains("/mybnb/getlisting")){
            this.handleGetListings(r);
        }
        else if (path.contains("/mybnb/register")){
            this.handleRegister(r);
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
                	Random rand = new Random();
                	int session = rand.nextInt();
                	
                	while(sessionUsername.containsKey(session) || session<=0) {
                		session = rand.nextInt();
                	}
                	
                	sessionUsername.put(session, username);
                	
                	JSONObject response = new JSONObject();
                    response.put("sessionId", session);
                    
                    String responseString = response.toString();
                    r.sendResponseHeaders(200, responseString.length());	
                    OutputStream os = r.getResponseBody();
                    os.write(responseString.getBytes());
                    os.close();
                	
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
   
    public void handleRegister(HttpExchange r) throws IOException {
    	String body = Utils.convert(r.getRequestBody());
    	
    	try {
            JSONObject deserialized = new JSONObject(body);
            String username, password, name, address, country, city, postal_code, dob, sin, payment_info;

            if (deserialized.has("username") && deserialized.has("password") && deserialized.has("name")
            	&& deserialized.has("address") && deserialized.has("country") && deserialized.has("city")
            	&& deserialized.has("postal_code") && deserialized.has("dob") && deserialized.has("sin")) {
            	
                username = deserialized.getString("username");
                password = deserialized.getString("password");
                name = deserialized.getString("name");
                address = deserialized.getString("address");
                country = deserialized.getString("country");
                city = deserialized.getString("city");
                postal_code = deserialized.getString("postal_code");
                dob = deserialized.getString("dob");
                sin = deserialized.getString("sin");
                
            } 
            else {
                r.sendResponseHeaders(400, -1);
                return;
            }

            try {
                if(!this.dao.checkUsername(username)) {
                	this.dao.RegisterUser(username, name, address, country, city, postal_code, dob, sin, password);
                	System.out.println("Registration Successful for " + username);
                    if(deserialized.has("payment_info")) {
                    	payment_info = deserialized.getString("payment_info");
                        this.dao.RegisterRenter(username, payment_info);
                        System.out.println("Registration of renter successful for " + username);
                    }
                    r.sendResponseHeaders(200, -1);
                    return;
                }
                else {
                	r.sendResponseHeaders(400, -1);
                	System.out.println("Register Failed, username taken");
                	return;
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
