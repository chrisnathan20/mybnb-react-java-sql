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

    public void insertPokemon(String name, String pid, String description, String type1, String type2) {
        return;
    }

    public String getPokemon(String pid){
    	return "";
    }
}
