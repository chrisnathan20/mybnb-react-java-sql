package main.Java;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
        else if (path.contains("/mybnb/getListingHost")){
            this.handleGetListingHost(r);
        }
        else if (path.contains("/mybnb/getRenterUpcomingListing")){
            this.handleGetRenterUpcomingListings(r);
        }
        else if (path.contains("/mybnb/getRenterCompletedListing")){
            this.handleGetRenterCompletedListings(r);
        }
        else if (path.contains("/mybnb/getHostUpcomingListing")){
            this.handleGetHostUpcomingListings(r);
        }
        else if (path.contains("/mybnb/getHostCompletedListing")){
            this.handleGetHostCompletedListings(r);
        }
        else if (path.contains("/mybnb/getunavailability")){
        	this.handleGetUnavailability(r);
        }
        else if (path.contains("/mybnb/getspecialprices")){
        	this.handleGetSpecialPrices(r);
        }
        else if (path.contains("/mybnb/gettopamenities")){
        	this.handleGetTopAmenities(r);
        }
        else if (path.contains("/mybnb/getlowestamenities")){
        	this.handleGetLowestAmenities(r);
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
            String sortby, address, city, country, postalcode, start, end;
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
                start = deserialized.getString("start_date");
                end = deserialized.getString("end_date");
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
            	String responseString = this.dao.getListings(sortby, address, city, country, postalcode, latitude, longitude, minprice, maxprice, distance, start, end);
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
    
    public void handleGetRenterUpcomingListings(HttpExchange r) throws IOException {
    	String path = r.getRequestURI().toString();
    	String[] arrOfStr = path.split("&");
        String username = sessionUsername.get(Integer.parseInt(arrOfStr[0].split("/")[3]));
        try {
            String response = this.dao.getRenterUpcomingListings(username);
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
    
    public void handleGetHostUpcomingListings(HttpExchange r) throws IOException {
    	String path = r.getRequestURI().toString();
    	String[] arrOfStr = path.split("&");
        String username = sessionUsername.get(Integer.parseInt(arrOfStr[0].split("/")[3]));
        try {
            String response = this.dao.getHostUpcomingListings(username);
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
    
    public void handleGetListingHost(HttpExchange r) throws IOException {
    	String path = r.getRequestURI().toString();
    	String[] arrOfStr = path.split("&");
        String username = sessionUsername.get(Integer.parseInt(arrOfStr[0].split("/")[3]));
        try {
            String response = this.dao.getListingsHost(username);
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
    
    public void handleGetRenterCompletedListings(HttpExchange r) throws IOException {
    	String path = r.getRequestURI().toString();
    	String[] arrOfStr = path.split("&");
        String username = sessionUsername.get(Integer.parseInt(arrOfStr[0].split("/")[3]));
        try {
            String response = this.dao.getRenterCompletedListings(username);
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
    
    public void handleGetHostCompletedListings(HttpExchange r) throws IOException {
    	String path = r.getRequestURI().toString();
    	String[] arrOfStr = path.split("&");
        String username = sessionUsername.get(Integer.parseInt(arrOfStr[0].split("/")[3]));
        try {
            String response = this.dao.getHostCompletedListings(username);
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
    
    public void handleGetTopAmenities(HttpExchange r) throws IOException {
        try {
            String response = this.dao.getTopAmenities();
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
    
    public void handleGetLowestAmenities(HttpExchange r) throws IOException {
        try {
            String response = this.dao.getLowestAmenities();
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
    
    public void handleGetUnavailability(HttpExchange r) throws IOException {
    	String path = r.getRequestURI().toString();
    	String[] arrOfStr = path.split("&");
    	System.out.println(path);
        System.out.println(arrOfStr[1]);
        try {
            String response = this.dao.getUnavailability(Integer.parseInt(arrOfStr[1]));
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
    
    public void handleGetSpecialPrices(HttpExchange r) throws IOException {
    	String path = r.getRequestURI().toString();
    	String[] arrOfStr = path.split("&");
    	System.out.println(path);
        System.out.println(arrOfStr[1]);
        try {
            String response = this.dao.getSpecialPrices(Integer.parseInt(arrOfStr[1]));
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
        else if (path.contains("/mybnb/deleteaccount")){
            this.handleDelete(r);
        }
        else if (path.contains("/mybnb/cancelbooking")){
            this.handleCancelBooking(r);
        }
        else if (path.contains("/mybnb/addCommentRenter")){
            this.handleAddCommentRenter(r);
        }
        else if (path.contains("/mybnb/addCommentHost")){
            this.handleAddCommentHost(r);
        }
        else if (path.contains("/mybnb/attemptbooking")){
            this.handleAttemptBooking(r);
        }
        else if (path.contains("/mybnb/booking")){
            this.handleBooking(r);
        }
        else if (path.contains("/mybnb/register")){
            this.handleRegister(r);
        }
        else if (path.contains("/mybnb/login")){
            this.handleLogin(r);
        }
        else if (path.contains("/mybnb/addlisting")){
            this.handleAddListing(r);
        }
        else {
            r.sendResponseHeaders(500, -1);
            return;
        }
    }
    
    public void handleAddListing(HttpExchange r) throws IOException {
    	String body = Utils.convert(r.getRequestBody());
    	
    	try {
            JSONObject deserialized = new JSONObject(body);
            Double longitude, latitude, basePrice;
            String address, country, city, postalcode, type, amenitiesChosen;
            
            longitude = deserialized.getDouble("longitude");
            latitude = deserialized.getDouble("latitude");
            basePrice = deserialized.getDouble("basePrice");
            
            address = deserialized.getString("address");
            country = deserialized.getString("country");
            city = deserialized.getString("city");
            postalcode = deserialized.getString("postal_code");
            type = deserialized.getString("type");
            amenitiesChosen = deserialized.getString("amenities");
                        
            String[] arr_am = amenitiesChosen.split(",");


            try {
            	String path = r.getRequestURI().toString();
            	String[] arrOfStr = path.split("&");
            	System.out.println(Integer.parseInt(arrOfStr[0].split("/")[3]));
                String username = sessionUsername.get(Integer.parseInt(arrOfStr[0].split("/")[3]));
                
                this.dao.addListing(username, longitude, latitude, basePrice, address, country, city, postalcode, type, arr_am);
                
                r.sendResponseHeaders(200, -1);
                
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
    
    public void handleAddCommentRenter(HttpExchange r) throws IOException {
    	String body = Utils.convert(r.getRequestBody());
    	
    	try {
            JSONObject deserialized = new JSONObject(body);
            int rating;
            String title, content;

            if (deserialized.has("rating") && deserialized.has("title") && deserialized.has("content")) {
            	rating = deserialized.getInt("rating");
            	title = deserialized.getString("title");
            	content = deserialized.getString("content");
            } else {
                r.sendResponseHeaders(400, -1);
                return;
            }

            try {
            	String path = r.getRequestURI().toString();
            	String[] arrOfStr = path.split("&");
            	System.out.println(Integer.parseInt(arrOfStr[0].split("/")[3]));
                String fromUsername = sessionUsername.get(Integer.parseInt(arrOfStr[0].split("/")[3]));
                int booking_id = Integer.parseInt(arrOfStr[1]);
                
                String forUsername = this.dao.getHostofBooking(booking_id);

                
                this.dao.addComment(fromUsername, forUsername, rating, title, content, booking_id);
                r.sendResponseHeaders(200, -1);
                
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
    
    public void handleAddCommentHost(HttpExchange r) throws IOException {
    	String body = Utils.convert(r.getRequestBody());
    	
    	try {
            JSONObject deserialized = new JSONObject(body);
            int rating;
            String title, content;

            if (deserialized.has("rating") && deserialized.has("title") && deserialized.has("content")) {
            	rating = deserialized.getInt("rating");
            	title = deserialized.getString("title");
            	content = deserialized.getString("content");
            } else {
                r.sendResponseHeaders(400, -1);
                return;
            }

            try {
            	String path = r.getRequestURI().toString();
            	String[] arrOfStr = path.split("&");
            	System.out.println(Integer.parseInt(arrOfStr[0].split("/")[3]));
                String fromUsername = sessionUsername.get(Integer.parseInt(arrOfStr[0].split("/")[3]));
                int booking_id = Integer.parseInt(arrOfStr[1]);
                
                String forUsername = this.dao.getRenterofBooking(booking_id);

                
                this.dao.addComment(fromUsername, forUsername, rating, title, content, booking_id);
                r.sendResponseHeaders(200, -1);
                
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
    
    
    public void handleDelete(HttpExchange r) throws IOException {
        try {
        	String path = r.getRequestURI().toString();
        	String[] arrOfStr = path.split("&");
        	System.out.println(Integer.parseInt(arrOfStr[0].split("/")[3]));
            String username = sessionUsername.get(Integer.parseInt(arrOfStr[0].split("/")[3]));
            
            this.dao.deleteUser(username);

            r.sendResponseHeaders(200, -1);
            
        } catch (Exception e) {
            r.sendResponseHeaders(500, -1);
            e.printStackTrace();
            return;
        }
    }

    public void handleCancelBooking(HttpExchange r) throws IOException {
    	String body = Utils.convert(r.getRequestBody());
    	
    	try {
            JSONObject deserialized = new JSONObject(body);
            int id;

            if (deserialized.has("booking_id")) {
                id = deserialized.getInt("booking_id");
            } else {
                r.sendResponseHeaders(400, -1);
                return;
            }

            try {
                this.dao.cancelBooking(id);
                r.sendResponseHeaders(200, -1);
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
                    
                    if(this.dao.isRenter(username)) {
                    	response.put("type", "renter");
                    }
                    else {
                    	response.put("type", "host");
                    }
                    
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
   
    public void handleAttemptBooking(HttpExchange r) throws IOException {
    	String body = Utils.convert(r.getRequestBody());
    	
    	try {
            JSONObject deserialized = new JSONObject(body);
            String start, end;

            if (deserialized.has("start_date") && deserialized.has("end_date")) {
                start = deserialized.getString("start_date");
                end = deserialized.getString("end_date");  
            } 
            else {
                r.sendResponseHeaders(400, -1);
                return;
            }

            try {
            	double total = 0;
            	String path = r.getRequestURI().toString();
            	String[] arrOfStr = path.split("&");
                int listing_id = Integer.parseInt(arrOfStr[1]);
                
                if(this.dao.isAvailable(listing_id, start, end)) {
                	
                	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                	Calendar c = Calendar.getInstance();
                	
                	
	                while(!start.equals(end)) {
	                	total = total + this.dao.getPrice(listing_id, start);
	                	
	                	c.setTime(sdf.parse(start));
	                	c.add(Calendar.DATE, 1);  // number of days to add
	                	start = sdf.format(c.getTime()); 	
	                	
	                	System.out.println(start);
	                }
	                
	                String responseString = Double.toString(total);
                    r.sendResponseHeaders(200, responseString.length());	
                    OutputStream os = r.getResponseBody();
                    os.write(responseString.getBytes());
                    os.close();
                }
                else {
                	r.sendResponseHeaders(400, -1);
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
    
    public void handleBooking(HttpExchange r) throws IOException {
        try {
        	String path = r.getRequestURI().toString();
        	String[] arrOfStr = path.split("&");
        	System.out.println(Integer.parseInt(arrOfStr[0].split("/")[3]));
            String username = sessionUsername.get(Integer.parseInt(arrOfStr[0].split("/")[3]));
            int listing_id = Integer.parseInt(arrOfStr[1]);
            String start = arrOfStr[2];
            String end = arrOfStr[3];
            String cost = arrOfStr[4];

            
            this.dao.addBooking(username, listing_id, start, end, cost);
            r.sendResponseHeaders(200, -1);
            
        } catch (Exception e) {
            r.sendResponseHeaders(500, -1);
            e.printStackTrace();
            return;
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
