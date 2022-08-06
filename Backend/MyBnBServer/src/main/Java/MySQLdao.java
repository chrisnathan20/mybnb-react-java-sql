package main.Java;

// Get ready to execute queries. 
import java.sql.*;

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
}
