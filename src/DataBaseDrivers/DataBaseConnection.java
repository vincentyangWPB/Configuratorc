package DataBaseDrivers;
import java.sql.*;

public class DataBaseConnection {
	private String URL = "jdbc:postgresql://52.39.252.245/assembly";
	private final String USER = "paul_risen";
	private final String PASSWORD = "ChAnGeMe";
	private Connection conn = null;
	
	/**
	 * default constructor
	 */
	public DataBaseConnection() {
		
	}
	
	/**
	 * get Connection
	 * @return 
	 * @throws SQLException
	 */
	public Connection connect() throws SQLException{
		return DriverManager.getConnection(URL,USER,PASSWORD);
	}
	
	/**
	 * open connection between java and postgres
	 */
	public void openConnection() {
		System.out.println("------- PostgreSQL " 
							+ "JDBC Connection Testing --------");
		try {
			Class.forName("org.postgresql.Driver");
			
		} catch(ClassNotFoundException ex) {
			System.out.println("Where is your PostgreSQL JDBC Driver? "
								+ "Includ in your library path!");
			ex.printStackTrace();
		}
		
		System.out.println("PostgreSQL JDBC Driver Registered!");
		
		
		try {
			conn = this.connect();
			
		} catch(SQLException ex) {
			System.out.println("Connection Failed! Check output console");
			ex.printStackTrace();
		}
		
		if(conn != null) {
			System.out.println("Connection Established");
		} else {
			System.out.println("Connection Failed");
		}
	}
	
	/**
	 * connection object getter
	 * @return
	 */
	public Connection getConn() {
		Connection newConn = conn;
		return newConn;
	}
	
	/**
	 * close connection
	 */
	public void closeConnection(){
		try {
			conn.close();
		} catch(SQLException ex) {
			System.err.println(ex.getMessage());
		}
	}
	
}
