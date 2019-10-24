package tuv.lib.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

final public class DBConnector {

	private static Connection con;

	private static final String url = "jdbc:mysql://localhost:3306/libr?useSSL=false";
	private static final String user = "root";
	private static final String password = "";

	public static void setUpConncetion() {

		String query = "SELECT VERSION()";
		String query2 = "SELECT USER_POSS\r\n" + 
				"FROM libr.users\r\n" + 
				"WHERE USER_NAME = \"Clara\" AND USER_PASSWORD = \"pass3\"; ";
		try {
			con = DriverManager.getConnection(url, user, password);

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query2);

			if (rs.next()) {

				System.out.println(rs.getInt(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static ResultSet executeQuery(String query) {
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
