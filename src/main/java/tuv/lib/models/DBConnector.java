package tuv.lib.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Static class that connects to the database
 * 
 * @author Zheni
 *
 */
final public class DBConnector {

	private static Connection con;

	private static final String url = "jdbc:mysql://localhost:3306/libr?useSSL=false";
	private static final String user = "zheni";
	private static final String password = "nikola";

	/**
	 * Sets up the url , username and password for the database connection  
	 */
	public static void setUpConncetion() {

		String query = "SELECT VERSION()";

		try {
			con = DriverManager.getConnection(url, user, password);

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			if (rs.next()) {

				System.out.println(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * If connection is set returns the connection
	 * if connection is not set, sets it and returns the connection
	 * 
	 * @return connection to the database
	 */
	public static Connection getConnection() {
		if (con == null) {
			setUpConncetion();
		}
		return con;
	}

	/**
	 * Closes the connection to the database
	 */
	public static void Disconnect() {
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static ResultSet executeQuery(String query) {
		setUpConncetion();
		ResultSet rs = null;
		try {
			Statement st = con.createStatement();
			rs = st.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

}
