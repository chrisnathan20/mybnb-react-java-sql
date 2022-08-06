package main.Java;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.sql.SQLException;

import org.json.JSONException;

public class Endpoint implements HttpHandler {

    public MySQLdao dao;

    public Endpoint() throws SQLException {
        this.dao = new MySQLdao();
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
        String body = Utils.convert(r.getRequestBody());
    	
    	try {
            r.sendResponseHeaders(200, -1);
        } catch (Exception e) {
            r.sendResponseHeaders(500, -1);
            e.printStackTrace();
            return;
        }
    }
    
    public void handlePost(HttpExchange r) throws IOException, JSONException {
    	try {
            r.sendResponseHeaders(200, -1);
        } catch (Exception e) {
            r.sendResponseHeaders(500, -1);
            e.printStackTrace();
            return;
        }
    }

}
