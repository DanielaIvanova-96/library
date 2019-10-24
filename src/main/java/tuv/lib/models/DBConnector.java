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

}
