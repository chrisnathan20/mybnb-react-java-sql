package main.Java;

// Get ready to execute queries. 
import java.sql.*;
import java.util.ArrayList;

import org.json.JSONObject;

public class MySQLdao {

	private final Connection connection;
	
    private final String url = "jdbc:mysql://127.0.0.1/mybnb";
    private final String username = "root";
    private final String password = "12345";


    public MySQLdao() throws SQLException {
    	this.connection = DriverManager.getConnection(url, username, password);
    }

    public boolean authenticateLogin(String username, String password) throws SQLException {
    	
    	PreparedStatement execStat=connection.prepareStatement("SELECT * FROM user WHERE username=\"" +
    	username + "\" and password=\"" + password + "\"");
    	ResultSet rs = execStat.executeQuery();
    	
    	return rs.next();
    }
    
    public boolean checkUsername(String username) throws SQLException {
    	
    	PreparedStatement execStat=connection.prepareStatement("SELECT * FROM user WHERE username=\"" +
    	username + "\"");
    	ResultSet rs = execStat.executeQuery();
    	
    	return rs.next();
    }
    
    public void RegisterUser(String username, String name, String address, String country, String city, String postal_code, String dob, String sin, String password) throws SQLException {
  
    	PreparedStatement execStat=connection.prepareStatement("INSERT INTO user(username, name, address, country, city, postal_code, dob, sin, password) values ('" 
    	+ username + "', '" + name + "', '" + address + "', '" + country + "', '" + city + "', '" + postal_code + "', '"  + dob + "', '" 
    	+ sin + "', '" + password + "')");
    	execStat.execute();
    	
    }
    
    public void RegisterRenter(String username, String payment_info) throws SQLException {
    	  
    	PreparedStatement execStat=connection.prepareStatement("INSERT INTO renter(username, payment_info) values ('" 
    	+ username + "', '" + payment_info + "')");
    	execStat.execute();
    	
    }
    
    public int calculateDistance(Double lat1, Double lon1, Double lat2, Double lon2){  // generally used geo measurement function
        Double R = 6378.137; // Radius of earth in KM
        Double dLat = lat2 * Math.PI / 180 - lat1 * Math.PI / 180;
        Double dLon = lon2 * Math.PI / 180 - lon1 * Math.PI / 180;
        Double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
        Math.cos(lat1 * Math.PI / 180) * Math.cos(lat2 * Math.PI / 180) *
        Math.sin(dLon/2) * Math.sin(dLon/2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        Double d = R * c;
        return (int) Math.round(d*1000); // meters
    }
    
    public String getViewListings(int listing_id) throws SQLException {
    	
    	PreparedStatement execStat=connection.prepareStatement("SELECT * FROM listing WHERE listing_id=" +
    	listing_id);
    	ResultSet rs = execStat.executeQuery();
    	rs.next();
    	
    	PreparedStatement execam=connection.prepareStatement("select * from mybnb.listing_amenity where listing_id = " + listing_id);
    	ResultSet rsam = execam.executeQuery();
    	
        PreparedStatement execType=connection.prepareStatement("select * from mybnb.listing_type where listing_id = " + listing_id);
    	ResultSet rsType = execType.executeQuery();
    	rsType.next();

    	String amenity = "";
    	
    	if(rsam.next()) {
    		amenity = amenity + rsam.getString("amenity_name");
    	}
    	
    	if(rsam.next()) {
    		amenity = amenity  + ", " + rsam.getString("amenity_name");
    	}
    	
    	if(rsam.next()) {
    		amenity = amenity  + ", " + rsam.getString("amenity_name") + " and more";
    	}
    	
    	JSONObject response = new JSONObject();
    	response.put("type", rsType.getString("type_name"));
    	response.put("address", rs.getString("address"));
    	response.put("country", rs.getString("country"));
    	response.put("city", rs.getString("city"));
    	response.put("postal_code", rs.getString("postal_code"));
    	response.put("price", rs.getDouble("base_price"));
    	response.put("latitude", rs.getDouble("latitude"));
    	response.put("longitude", rs.getDouble("longitude"));
    	response.put("amenities", amenity);
    	
    	return response.toString();
    }
    
    public String getListings(String sortby, String address, String city, String country, String postalcode, Double latitude, Double longitude, Double minprice, Double maxprice, int distance, String start, String end) throws SQLException {

    	PreparedStatement execStat=connection.prepareStatement("SELECT * from mybnb.listing where address like '%" + address + "%' and country like '%" + country + "%' and city like '%" + city + "%' and postal_code like '%" + postalcode + "%' and base_price >= " + minprice + " and base_price <= " + maxprice + "order by base_price ASC");
    	ResultSet rs = execStat.executeQuery();
    	
    	ArrayList<JSONObject> response = new ArrayList<JSONObject>();
    	
    	while (rs.next()) {
    		
        	JSONObject listing = new JSONObject();
            listing.put("listingId", rs.getInt("listing_id"));
            
            PreparedStatement execType=connection.prepareStatement("select * from mybnb.listing_type where listing_id = " + rs.getInt("listing_id"));
        	ResultSet rsType = execType.executeQuery();
        	rsType.next();
        	listing.put("type", rsType.getString("type_name"));
        	
        	PreparedStatement execam=connection.prepareStatement("select * from mybnb.listing_amenity where listing_id = " + rs.getInt("listing_id"));
        	ResultSet rsam = execam.executeQuery();
        	
        	String amenity = "";
        	ArrayList<String> amenity_list = new ArrayList<String>();
        	
        	if(rsam.next()) {
        		amenity = amenity + rsam.getString("amenity_name");
        		amenity_list.add(rsam.getString("amenity_name"));
        	}
        	
        	if(rsam.next()) {
        		amenity = amenity  + ", " + rsam.getString("amenity_name");
        		amenity_list.add(rsam.getString("amenity_name"));
        	}
        	
        	if(rsam.next()) {
        		amenity = amenity  + ", " + rsam.getString("amenity_name") + " and more";
        		amenity_list.add(rsam.getString("amenity_name"));
        	}
        	
        	while(rsam.next()) {
        		amenity_list.add(rsam.getString("amenity_name"));
        	}
        	
        	listing.put("type", rsType.getString("type_name"));
        	listing.put("amenities", amenity);
        	listing.put("am_list", amenity_list.toString());
            
    		listing.put("longitude", rs.getDouble("longitude"));
    		listing.put("latitude", rs.getDouble("latitude"));
    		listing.put("address", rs.getString("address"));
    		listing.put("country", rs.getString("country"));
    		listing.put("city", rs.getString("city"));
    		listing.put("postal_code", rs.getString("postal_code"));
    		listing.put("price", rs.getDouble("base_price"));
    		listing.put("distance", calculateDistance(latitude, longitude, rs.getDouble("latitude"), rs.getDouble("longitude")));
    		
    		if(this.isAvailable(rs.getInt("listing_id"), start, end)) {
	    		if((int)listing.get("distance") <= distance) {
	    			if(sortby.equals("distance")){
	    				int i = 0;
	    				boolean added = false;
	    				
	    				while(i < response.size() && !added) {
	    					if ((int)response.get(i).get("distance") > (int)listing.get("distance")) {
	    						added = true;
	    						response.add(i, listing);
	    					}
	    					i++;
	    				}
	    				
	    				if(!added) {
	    					response.add(listing);
	    				}
	    			}else {
	    				response.add(listing);
	    			}
	    		}
    		}
    	}

    	
    	System.out.println(response.toString());
    	return response.toString();
    }
    
    public boolean isAvailable(int id, String start, String end) throws SQLException {
    	PreparedStatement execStat1=connection.prepareStatement("select * from mybnb.listing_unavailability where listing_id ="+ id +" and '"+ start +"' between initial_date and end_date");
    	ResultSet rs1 = execStat1.executeQuery();
    	PreparedStatement execStat2=connection.prepareStatement("select * from mybnb.listing_unavailability where listing_id ="+ id +" and '"+ end +"' between initial_date and end_date");
    	ResultSet rs2 = execStat2.executeQuery();
    	PreparedStatement execStat3=connection.prepareStatement("select * from mybnb.listing_unavailability where listing_id ="+ id +" and initial_date between " + start + " and " + end);
    	ResultSet rs3 = execStat3.executeQuery();
    	
    	return !(rs1.next() || rs2.next() || rs3.next());
    }
}
