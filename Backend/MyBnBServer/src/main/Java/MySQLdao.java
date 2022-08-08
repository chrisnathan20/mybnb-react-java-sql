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
    
    public String getUnavailability(int listing_id) throws SQLException {
    	
    	PreparedStatement execStat=connection.prepareStatement("SELECT * FROM listing_unavailability WHERE listing_id=" +
    	listing_id + " AND initial_date IS NOT NULL");
    	ResultSet rs = execStat.executeQuery();

    	ArrayList<JSONObject> response = new ArrayList<JSONObject>();
    	
    	while(rs.next()) {
    		JSONObject unavail = new JSONObject();
    		unavail.put("start_date", rs.getDate("initial_date"));
    		unavail.put("end_date", rs.getDate("end_date"));
    		response.add(unavail);
    	}
    	return response.toString();
    }
    
    public String getSpecialPrices(int listing_id) throws SQLException {
    	
    	PreparedStatement execStat=connection.prepareStatement("SELECT * FROM special_prices WHERE listing_id=" +
    	listing_id + " AND initial_date IS NOT NULL");
    	ResultSet rs = execStat.executeQuery();

    	ArrayList<JSONObject> response = new ArrayList<JSONObject>();
    	
    	while(rs.next()) {
    		JSONObject special = new JSONObject();
    		special.put("start_date", rs.getDate("initial_date"));
    		special.put("end_date", rs.getDate("end_date"));
    		special.put("price", rs.getDouble("price"));
    		
    		response.add(special);
    	}
    	return response.toString();
    }
    
    public String getTopAmenities() throws SQLException {
    	
    	PreparedStatement execStat=connection.prepareStatement("SELECT amenity_name, count(*) AS count \r\n"
    			+ "FROM listing_amenity\r\n"
    			+ "GROUP BY amenity_name\r\n"
    			+ "ORDER BY count(*) DESC\r\n"
    			+ "LIMIT 3;");
    	ResultSet rs = execStat.executeQuery();

    	ArrayList<JSONObject> response = new ArrayList<JSONObject>();
    	
    	while(rs.next()) {
    		JSONObject amenities = new JSONObject();
    		amenities.put("name", rs.getString("amenity_name"));
    		amenities.put("count", rs.getInt("count"));

    		response.add(amenities);
    	}
    	return response.toString();
    }
    
    public String getLowestAmenities() throws SQLException {
    	
    	PreparedStatement execStat=connection.prepareStatement("SELECT amenity_name, count(*) AS count \r\n"
    			+ "FROM listing_amenity\r\n"
    			+ "GROUP BY amenity_name\r\n"
    			+ "ORDER BY count(*) ASC\r\n"
    			+ "LIMIT 3;");
    	ResultSet rs = execStat.executeQuery();

    	ArrayList<JSONObject> response = new ArrayList<JSONObject>();
    	
    	while(rs.next()) {
    		JSONObject amenities = new JSONObject();
    		amenities.put("name", rs.getString("amenity_name"));
    		amenities.put("count", rs.getInt("count"));

    		response.add(amenities);
    	}
    	return response.toString();
    }
    
    public String getReportA(String city, String start, String end) throws SQLException {
    	
    	PreparedStatement execStat=connection.prepareStatement("SELECT count(*) AS count\r\n"
    			+ "from \r\n"
    			+ "(SELECT  booking_id, initial_date, end_date, listing.listing_id, listing.city\r\n"
    			+ "from\r\n"
    			+ "(SELECT booking_id, initial_date, end_date, listing_id\r\n"
    			+ "from booking, listing_unavailability\r\n"
    			+ "WHERE unavail_id = booking_id) AS BookingUnavail, listing\r\n"
    			+ "where BookingUnavail.listing_id = listing.listing_id) as BookingListing\r\n"
    			+ "WHERE city='" +  city + "' and initial_date between \'" + start + "\' and \'" + end  
    			+ "' and end_date between \'" + start + "\' and \'" + end + "'");
    	ResultSet rs = execStat.executeQuery();
    	
    	rs.next();
    	
    	return rs.getString("count").toString();
    }
    
    public String getReportB(String zip, String start, String end) throws SQLException {
    	
    	PreparedStatement execStat=connection.prepareStatement("SELECT count(*) AS count\r\n"
    			+ "from \r\n"
    			+ "(SELECT  booking_id, initial_date, end_date, listing.listing_id, listing.city\r\n"
    			+ "from\r\n"
    			+ "(SELECT booking_id, initial_date, end_date, listing_id\r\n"
    			+ "from booking, listing_unavailability\r\n"
    			+ "WHERE unavail_id = booking_id) AS BookingUnavail, listing\r\n"
    			+ "where BookingUnavail.listing_id = listing.listing_id) as BookingListing\r\n"
    			+ "WHERE postal_code='" +  zip + "' and initial_date between \'" + start + "\' and \'" + end  
    			+ "' and end_date between \'" + start + "\' and \'" + end + "'");
    	ResultSet rs = execStat.executeQuery();
    	
    	rs.next();
    	
    	return rs.getString("count").toString();
    }
    
    public String getReportC(String country) throws SQLException {
    	
    	PreparedStatement execStat=connection.prepareStatement("SELECT count(*) as count\r\n"
    			+ "from listing\r\n"
    			+ "where country=\""+country+"\"");
    	ResultSet rs = execStat.executeQuery();
    	
    	rs.next();
    	
    	return rs.getString("count").toString();
    }
    
    public String getReportD(String country, String city) throws SQLException {
    	
    	PreparedStatement execStat=connection.prepareStatement("SELECT count(*) as count\r\n"
    			+ "from listing\r\n"
    			+ "where country=\"" + country + "\" AND city=\"" + city + "\"");
    	ResultSet rs = execStat.executeQuery();
    	
    	rs.next();
    	
    	return rs.getString("count").toString();
    }
    
    public String getReportE(String country, String city, String postal_code) throws SQLException {
    	
    	PreparedStatement execStat=connection.prepareStatement("SELECT count(*) as count\r\n"
    			+ "from listing\r\n"
    			+ "where country=\"" + country + "\" AND city=\"" + city + "\" AND postal_code=\"" + postal_code + "\"");
    	ResultSet rs = execStat.executeQuery();
    	
    	rs.next();
    	
    	return rs.getString("count").toString();
    }
    
    public String getReportF(String country) throws SQLException {
    	
    	PreparedStatement execStat=connection.prepareStatement("SELECT username, count(*) as count\r\n"
    			+ "from (SELECT username, listing.listing_id, country, city\r\n"
    			+ "from listing, host_listing\r\n"
    			+ "where listing.listing_id = host_listing.listing_id\r\n"
    			+ "and country=\""+ country + "\")" + "as listingHost\r\n"
    			+ "group by username\r\n"
    			+ "order by count(*) DESC;");
    	ResultSet rs = execStat.executeQuery();
    	ArrayList<JSONObject> response = new ArrayList<JSONObject>();
    	
    	while(rs.next()) {
    		JSONObject host = new JSONObject();
    		host.put("host", rs.getString("username"));

    		response.add(host);
    	}
    	return response.toString();
    }
    
    public String getReportG(String city) throws SQLException {
    	
    	PreparedStatement execStat=connection.prepareStatement("SELECT username, count(*) as count\r\n"
    			+ "from (SELECT username, listing.listing_id, country, city\r\n"
    			+ "from listing, host_listing\r\n"
    			+ "where listing.listing_id = host_listing.listing_id\r\n"
    			+ "and city=\""+ city + "\")" + "as listingHost\r\n"
    			+ "group by username\r\n"
    			+ "order by count(*) DESC;");
    	ResultSet rs = execStat.executeQuery();
    	ArrayList<JSONObject> response = new ArrayList<JSONObject>();
    	
    	while(rs.next()) {
    		JSONObject host = new JSONObject();
    		host.put("host", rs.getString("username"));

    		response.add(host);
    	}
    	return response.toString();
    }
    
    public String getReportI(String start, String end) throws SQLException {
    	
    	PreparedStatement execStat=connection.prepareStatement("select username\r\n"
    			+ "from\r\n"
    			+ "(select * \r\n"
    			+ "from booking_renter, listing_unavailability\r\n"
    			+ "where booking_id = unavail_id\r\n"
    			+ "and initial_date between \"" + start + "\" and \"" + end + "\" \r\n"
    			+ "and end_date between \"" + start + "\" and \"" + end + "\") as temp\r\n"
    			+ "group by username\r\n"
    			+ "order by count(*) DESC;");
    	ResultSet rs = execStat.executeQuery();
    	ArrayList<JSONObject> response = new ArrayList<JSONObject>();
    	
    	while(rs.next()) {
    		JSONObject host = new JSONObject();
    		host.put("host", rs.getString("username"));

    		response.add(host);
    	}
    	return response.toString();
    }
    
    public String getReportJ(String start, String end, String city) throws SQLException {
    	
    	PreparedStatement execStat=connection.prepareStatement("select username, count(*)\r\n"
    			+ "from\r\n"
    			+ "(select listing.listing_id, city, username, booking_id\r\n"
    			+ "from booking_renter, listing_unavailability, listing\r\n"
    			+ "where booking_id = unavail_id\r\n"
    			+ "and listing.listing_id = listing_unavailability.listing_id\r\n"
    			+ "and initial_date between \"" + start + "\" and \"" + end + "\"\r\n"
    			+ "and end_date between \"" + start + "\" and \"" + end + "\"\r\n"
    			+ "and city = '" + city + "') as temp\r\n"
    			+ "group by username\r\n"
    			+ "order by count(*) desc;");
    	ResultSet rs = execStat.executeQuery();
    	ArrayList<JSONObject> response = new ArrayList<JSONObject>();
    	
    	while(rs.next()) {
    		JSONObject host = new JSONObject();
    		host.put("host", rs.getString("username"));

    		response.add(host);
    	}
    	return response.toString();
    }
    
    public String getReportK() throws SQLException {
    	
    	PreparedStatement execStat=connection.prepareStatement("SELECT username, count(*) as count\r\n"
    			+ "from (SELECT booking.booking_id, username\r\n"
    			+ "from booking, booking_renter\r\n"
    			+ "where booking.booking_id = booking_renter.booking_id and status='cancelled') as booking_user\r\n"
    			+ "group by username\r\n"
    			+ "order by count(*) DESC\r\n"
    			+ "limit 1;");
    	ResultSet rs = execStat.executeQuery();
    	ArrayList<JSONObject> response = new ArrayList<JSONObject>();
    	
    	while(rs.next()) {
    		JSONObject host = new JSONObject();
    		host.put("username", rs.getString("username"));

    		response.add(host);
    	}
    	return response.toString();
    }
    
    public String getReportL() throws SQLException {
    	
    	PreparedStatement execStat=connection.prepareStatement("SELECT count(*)\r\n"
    			+ "from \r\n"
    			+ "(SELECT  booking_id, initial_date, end_date, listing.listing_id, listing.city\r\n"
    			+ "from\r\n"
    			+ "(SELECT booking_id, initial_date, end_date, listing_id\r\n"
    			+ "from booking, listing_unavailability\r\n"
    			+ "WHERE unavail_id = booking_id) AS BookingUnavail, listing\r\n"
    			+ "where BookingUnavail.listing_id = listing.listing_id) as BookingListing\r\n"
    			+ "WHERE city=\"Scarborough\";");
    	ResultSet rs = execStat.executeQuery();
    	ArrayList<JSONObject> response = new ArrayList<JSONObject>();
    	
    	while(rs.next()) {
    		JSONObject host = new JSONObject();
    		host.put("username", rs.getString("username"));

    		response.add(host);
    	}
    	return response.toString();
    }
    
    public String getListings(String sortby, String address, String city, String country, String postalcode, Double latitude, Double longitude, Double minprice, Double maxprice, int distance, String start, String end) throws SQLException {
    	
    	PreparedStatement execStat;
    	if(sortby.equals("price ascending")) {
    		execStat=connection.prepareStatement("SELECT * from mybnb.listing where address like '%" + address + "%' and country like '%" + country + "%' and city like '%" + city + "%' and postal_code like '%" + postalcode + "%' and base_price >= " + minprice + " and base_price <= " + maxprice + " and latitude IS NOT NULL order by base_price ASC");
    	}
    	else {
    		execStat=connection.prepareStatement("SELECT * from mybnb.listing where address like '%" + address + "%' and country like '%" + country + "%' and city like '%" + city + "%' and postal_code like '%" + postalcode + "%' and base_price >= " + minprice + " and base_price <= " + maxprice + " and latitude IS NOT NULL order by base_price DESC");
    	}
    	ResultSet rs = execStat.executeQuery();
    	
    	ArrayList<JSONObject> response = new ArrayList<JSONObject>();
    	
    	System.out.println(start);
    	
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
    	return response.toString();
    }
    
    public String getListingsHost(String username) throws SQLException {
    	
    	PreparedStatement execStat=connection.prepareStatement("SELECT * from mybnb.listing INNER JOIN host_listing ON listing.listing_id=host_listing.listing_id where host_listing.username = '" + username + "'");
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
    		
	    	response.add(listing);
    	}
    	return response.toString();
    }
    
    public void addUnavailable(int listing_id, String start, String end) throws SQLException {
    	
    	PreparedStatement execStat1=connection.prepareStatement("insert into listing_unavailability(initial_date, end_date, listing_id) values('" + start + "', '" + end + "', " + listing_id + ")");
    	execStat1.execute();
    }
    
    public void addSpecial(int listing_id, String start, String end, Double price) throws SQLException {
    	PreparedStatement execStat1=connection.prepareStatement("insert into Special_prices(listing_id, initial_date, end_date, price) values(" + listing_id + ", '" + start + "', '" + end + "', " + price +")");
    	execStat1.execute();
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
    
    public double getPrice(int id, String date) throws SQLException {
    	double cost = -1;
    	PreparedStatement execStat1=connection.prepareStatement("select * from mybnb.special_prices where listing_id = " + id + " and '" + date + "' between initial_date and end_date");
    	ResultSet rs1 = execStat1.executeQuery();
    	
    	if(rs1.next()){
    		cost = rs1.getDouble("price");
    	}
    	
    	if(cost == -1) {
        	PreparedStatement execStat2=connection.prepareStatement("select * from mybnb.listing where listing_id = " + id);
        	ResultSet rs2 = execStat2.executeQuery();
        	
        	if(rs2.next()){
        		cost = rs2.getDouble("base_price");
        	}
    	}
    	
    	return cost;
    	
    }
    
    public void addBooking(String username, int listing_id, String start, String end, String cost) throws SQLException {
    	
    	PreparedStatement execStat1=connection.prepareStatement("insert into listing_unavailability(initial_date, end_date, listing_id) values('" + start + "', '" + end + "'," + listing_id + ")");
    	execStat1.execute();
    	
    	
    	PreparedStatement execStat2=connection.prepareStatement("select * from mybnb.listing_unavailability order by unavail_id DESC;");
    	ResultSet rs = execStat2.executeQuery();
    	
    	rs.next();
    	
    	int booking_id = rs.getInt("unavail_id");
    	System.out.println(booking_id);
    	
    	PreparedStatement execStat3=connection.prepareStatement("insert into booking(booking_id, total_cost, status) values(" + booking_id + ", " + cost + ", 'upcoming')");
    	execStat3.execute();
    	
    	execStat3=connection.prepareStatement("insert into booking_renter(booking_id, username) values(" + booking_id + ", '" + username + "');");
    	execStat3.execute();
    	
    	
    }
    
    public void cancelBooking(int id) throws SQLException {
    	
    	PreparedStatement execStat1=connection.prepareStatement("update listing_unavailability set initial_date = NULL, end_date = NULL where unavail_id = " + id);
    	execStat1.execute();
    	
    	
    	PreparedStatement execStat3=connection.prepareStatement("update booking set status = 'cancelled' where booking_id = " + id);
    	execStat3.execute();    	
    	
    }
    
    public boolean isRenter(String username) throws SQLException {
    	
    	PreparedStatement execStat2=connection.prepareStatement("select * from mybnb.renter where username = '" + username + "'");
    	ResultSet rs = execStat2.executeQuery();
    	
    	return rs.next(); 	
    	
    }
    
    public String getHostofBooking(int booking_id) throws SQLException {
    	
    	PreparedStatement execStat2=connection.prepareStatement("select * from mybnb.listing_unavailability where unavail_id =" + booking_id);
    	ResultSet rs = execStat2.executeQuery();
    	
    	rs.next();
    	
    	int listing_id = rs.getInt("listing_id");
    	
    	PreparedStatement execStat=connection.prepareStatement("select * from mybnb.host_listing where listing_id = " + listing_id);
    	ResultSet rs1 = execStat.executeQuery();
    	
    	rs1.next();
    	
    	return rs1.getString("username");
    	
    }
    
    public String getRenterofBooking(int booking_id) throws SQLException {
    	
    	PreparedStatement execStat2=connection.prepareStatement("select * from mybnb.booking_renter where booking_id =" + booking_id);
    	ResultSet rs = execStat2.executeQuery();
    	
    	rs.next();
    	
    	return rs.getString("username");
    	
    }
    
    public void addComment(String fromUsername, String forUsername, int rating, String title, String content, int booking_id) throws SQLException {
    	
    	PreparedStatement execStat1=connection.prepareStatement("insert into Comment(title, content, rating, forUsername, fromUsername) values('" + title + "', '" + content + "'," + rating + ", '" + forUsername + "', '"+ fromUsername + "')");
    	execStat1.execute();
    	
    	
    	PreparedStatement execStat2=connection.prepareStatement("select * from mybnb.Comment order by comment_id DESC;");
    	ResultSet rs = execStat2.executeQuery();
    	
    	rs.next();
    	
    	int comment_id = rs.getInt("comment_id");
    	System.out.println(booking_id);
    	
    	PreparedStatement execStat3=connection.prepareStatement("insert into booking_comment(booking_id, comment_id) values(" + booking_id + ", " + comment_id + ");");
    	execStat3.execute();
    	
    	
    }
    
    public String getRenterUpcomingListings(String username) throws SQLException {
    	PreparedStatement execStat2=connection.prepareStatement("SELECT host_listing.username, booking.booking_id, booking.total_cost, listing_unavailability.initial_date, listing_unavailability.end_date, listing.address, listing.city, listing.country, listing_type.type_name FROM booking"
    			+ " INNER JOIN booking_renter ON booking.booking_id=booking_renter.booking_id"
    			+ " INNER JOIN listing_unavailability ON booking.booking_id=listing_unavailability.unavail_id"
    			+ " INNER JOIN listing ON listing.listing_id=listing_unavailability.listing_id"
    			+ " INNER JOIN listing_type ON listing.listing_id=listing_type.listing_id"
    			+ " INNER JOIN host_listing ON listing.listing_id=host_listing.listing_id"
    			+ " WHERE booking_renter.username = '" + username + "' and booking.status = 'upcoming';");
    	ResultSet rs = execStat2.executeQuery();
    	
    	ArrayList<JSONObject> response = new ArrayList<JSONObject>();
    	
    	while(rs.next()) {
    		JSONObject res = new JSONObject();
    		res.put("username", rs.getString("username"));
    		res.put("type", rs.getString("type_name"));
    		res.put("address", rs.getString("address"));
    		res.put("city", rs.getString("city"));
    		res.put("country", rs.getString("country"));
    		res.put("start_date", rs.getDate("initial_date"));
    		res.put("end_date", rs.getDate("end_date"));
    		res.put("total_cost", rs.getDouble("total_cost"));
    		res.put("booking_id", rs.getDouble("booking_id"));
    		response.add(res);
    	}
    	
    	return response.toString();
    }
    
    public String getHostUpcomingListings(String username) throws SQLException {
    	PreparedStatement execStat2=connection.prepareStatement("SELECT booking_renter.username, booking.booking_id, booking.total_cost, listing_unavailability.initial_date, listing_unavailability.end_date, listing.address, listing.city, listing.country, listing_type.type_name FROM booking"
    			+ " INNER JOIN booking_renter ON booking.booking_id=booking_renter.booking_id"
    			+ " INNER JOIN listing_unavailability ON booking.booking_id=listing_unavailability.unavail_id"
    			+ " INNER JOIN listing ON listing.listing_id=listing_unavailability.listing_id"
    			+ " INNER JOIN listing_type ON listing.listing_id=listing_type.listing_id"
    			+ " INNER JOIN host_listing ON listing.listing_id=host_listing.listing_id"
    			+ " WHERE host_listing.username = '" + username + "' and booking.status = 'upcoming';");
    	ResultSet rs = execStat2.executeQuery();
    	
    	ArrayList<JSONObject> response = new ArrayList<JSONObject>();
    	
    	while(rs.next()) {
    		JSONObject res = new JSONObject();
    		res.put("username", rs.getString("username"));
    		res.put("type", rs.getString("type_name"));
    		res.put("address", rs.getString("address"));
    		res.put("city", rs.getString("city"));
    		res.put("country", rs.getString("country"));
    		res.put("start_date", rs.getDate("initial_date"));
    		res.put("end_date", rs.getDate("end_date"));
    		res.put("total_cost", rs.getDouble("total_cost"));
    		res.put("booking_id", rs.getDouble("booking_id"));
    		response.add(res);
    	}
    	
    	return response.toString();
    }
    
    public String getRenterCompletedListings(String username) throws SQLException {
    	PreparedStatement execStat2=connection.prepareStatement("SELECT host_listing.username, booking.booking_id, booking.total_cost, listing_unavailability.initial_date, listing_unavailability.end_date, listing.address, listing.city, listing.country, listing_type.type_name FROM booking"
    			+ " INNER JOIN booking_renter ON booking.booking_id=booking_renter.booking_id"
    			+ " INNER JOIN listing_unavailability ON booking.booking_id=listing_unavailability.unavail_id"
    			+ " INNER JOIN listing ON listing.listing_id=listing_unavailability.listing_id"
    			+ " INNER JOIN listing_type ON listing.listing_id=listing_type.listing_id"
    			+ " INNER JOIN host_listing ON listing.listing_id=host_listing.listing_id"
    			+ " WHERE booking_renter.username = '" + username + "' and booking.status = 'completed';");
    	ResultSet rs = execStat2.executeQuery();

    	ArrayList<JSONObject> response = new ArrayList<JSONObject>();
    	
    	while(rs.next()) {
    		JSONObject res = new JSONObject();
    		res.put("username", rs.getString("username"));
    		res.put("type", rs.getString("type_name"));
    		res.put("address", rs.getString("address"));
    		res.put("city", rs.getString("city"));
    		res.put("country", rs.getString("country"));
    		res.put("start_date", rs.getDate("initial_date"));
    		res.put("end_date", rs.getDate("end_date"));
    		res.put("total_cost", rs.getDouble("total_cost"));
    		res.put("booking_id", rs.getDouble("booking_id"));
    		response.add(res);
    	}
    	
    	return response.toString();
    }
    
    public String getHostCompletedListings(String username) throws SQLException {
    	PreparedStatement execStat2=connection.prepareStatement("SELECT booking_renter.username, booking.booking_id, booking.total_cost, listing_unavailability.initial_date, listing_unavailability.end_date, listing.address, listing.city, listing.country, listing_type.type_name FROM booking"
    			+ " INNER JOIN booking_renter ON booking.booking_id=booking_renter.booking_id"
    			+ " INNER JOIN listing_unavailability ON booking.booking_id=listing_unavailability.unavail_id"
    			+ " INNER JOIN listing ON listing.listing_id=listing_unavailability.listing_id"
    			+ " INNER JOIN listing_type ON listing.listing_id=listing_type.listing_id"
    			+ " INNER JOIN host_listing ON listing.listing_id=host_listing.listing_id"
    			+ " WHERE host_listing.username = '" + username + "' and booking.status = 'completed';");
    	ResultSet rs = execStat2.executeQuery();

    	ArrayList<JSONObject> response = new ArrayList<JSONObject>();
    	
    	while(rs.next()) {
    		JSONObject res = new JSONObject();
    		res.put("username", rs.getString("username"));
    		res.put("type", rs.getString("type_name"));
    		res.put("address", rs.getString("address"));
    		res.put("city", rs.getString("city"));
    		res.put("country", rs.getString("country"));
    		res.put("start_date", rs.getDate("initial_date"));
    		res.put("end_date", rs.getDate("end_date"));
    		res.put("total_cost", rs.getDouble("total_cost"));
    		res.put("booking_id", rs.getDouble("booking_id"));
    		response.add(res);
    	}
    	
    	return response.toString();
    }
    
    public void deleteUser(String username) throws SQLException {
    	PreparedStatement execStat1=connection.prepareStatement("update user set password = NULL where username = \"" + username + "\"");
    	execStat1.execute();
    }
    
    
    public void addListing(String username, Double longitude, Double latitude, Double basePrice, String address, String country, String city, String postalcode, String type, String [] arr_am) throws SQLException {
    	PreparedStatement execStat3=connection.prepareStatement("insert into listing(longitude, latitude, address, country, city, postal_code, base_price) values (" + longitude + "," + latitude + ", '" + address + "', '" + country + "', '" + city + "', '" + postalcode + "'," + basePrice + ")");
    	execStat3.execute();
    	
    	PreparedStatement execStat2=connection.prepareStatement("select * from mybnb.listing order by listing_id DESC;");
    	ResultSet rs = execStat2.executeQuery();
    	
    	rs.next();
    	
    	int id = rs.getInt("listing_id");
    
    	PreparedStatement execStat=connection.prepareStatement("insert into host_listing(username, listing_id) values('" + username + "', " + id + ");");
    	execStat.execute();
    	
    	execStat=connection.prepareStatement("insert into listing_type(listing_id, type_name) values(" + id + ", '" + type + "');");
    	execStat.execute();
    	
    	for(String amenity: arr_am) {
    		execStat=connection.prepareStatement("insert into listing_amenity(listing_id, amenity_name) values(" + id + ", '" + amenity + "');");
        	execStat.execute();
    	}
    
    }
    
    public String getReportH(String city, String country) throws SQLException {
    	PreparedStatement execStat2=connection.prepareStatement("select count(listing_id) AS res from mybnb.listing where city = '" + city + "' and country = '" + country + "';");
    	ResultSet rs = execStat2.executeQuery();
    	
    	rs.next();
    	int i = rs.getInt("res") / 10;
    	
    	execStat2=connection.prepareStatement("select count(listing.listing_id) as count, username from mybnb.listing inner join host_listing ON listing.listing_id=host_listing.listing_id where city = '" + city + "' and country = '" + country + "' group by username;");
    	rs = execStat2.executeQuery();
    	
    	ArrayList<String> response = new ArrayList<String>();
    	
    	while(rs.next()) {
    		if(rs.getInt("count")>i) {
    			response.add(rs.getString("username"));
    		}
    	}
    	
    	
    	return response.toString();
    	
    }
}
