package tuv.lib.models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
import tuv.lib.models.Client;
import tuv.lib.models.DBConnector;
import tuv.lib.models.User;
import tuv.lib.models.User.Possition;

public class UserDAOImpl implements UserDAO {

	//private SessionFactory sessionFactory;

	// public void setSessionFactory(SessionFactory sf){
	// this.sessionFactory = sf;
	// }

	
	
	public User getUserById(int id) {	
		return null;
	}

	public void addUser(User u) {
		// INSERT INTO libr.users (USER_NAME, USER_PASSWORD, USER_POSS)
		// VALUES ('Logi', 'pass5', '1');

		String query = "INSERT INTO libr.users (USER_NAME, USER_PASSWORD, USER_POSS)\r\n" + "VALUES ('" + u.getName()
				+ "', '" + u.getPassword() + "', '" + u.getPosstion().ordinal() + "');";

		Connection con = DBConnector.getConnection();
		ResultSet rs;

		try {
			Statement st = con.createStatement();
			st.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void addClient(Client cl) {
		try {
			Connection con = DBConnector.getConnection();
			PreparedStatement stmt = con.prepareStatement("INSERT INTO libr.users VALUES (default,?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, cl.getName());
			stmt.setString(2, cl.getPassword());
			stmt.setInt(3, cl.getPosstion().ordinal());
			stmt.setInt(4, 0);
			stmt.setString(5, cl.getPhoneNum());
			stmt.setString(6, cl.getRecordDate());

			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateUser(User u) {
		
	}

	public void removeUser(int id) {
		// TODO Auto-generated method stub

	}

	public List<User> listUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getUserPos(String name, String password) {
		String query = "SELECT USER_POSS\r\n" + "FROM libr.users\r\n" + "WHERE USER_NAME = \"" + name
				+ "\" AND USER_PASSWORD = \"" + password + "\"; ";

		Connection con = DBConnector.getConnection();
		Statement st;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	public List<Client> findClients(String name) {

		Connection con = DBConnector.getConnection();
		Statement st;
		List<Client> clinetsRes = new ArrayList<Client>();
		try {
			st = con.createStatement();

			String query = "SELECT USER_NAME, USER_PH_NUM, USER_REC_DATE, USER_LOYALTY\r" + "FROM libr.users\r\n"
					+ "WHERE USER_NAME LIKE \"%" + name + "%\"; ";

			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				String res_name = rs.getString("USER_NAME");
				String res_phone = rs.getString("USER_PH_NUM");
				String res_date = rs.getString("USER_REC_DATE");
				int res_loyalty = rs.getInt("USER_LOYALTY");

				clinetsRes.add(new Client(res_name, res_phone, res_date, res_loyalty));
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clinetsRes;
	}

	public List<Client> classifyClients(String classProperty) {
		Connection con = DBConnector.getConnection();
		Statement st;
		String query = "SELECT USER_NAME, USER_PH_NUM, USER_REC_DATE, USER_LOYALTY FROM libr.users WHERE USER_POSS = 2  ";

		if (classProperty.equals("loyalty")) {
			query = query + " and USER_LOYALTY >0 ORDER BY USER_LOYALTY ;";

		} else {
			query = query + "ORDER BY USER_REC_DATE ;";
		}

		List<Client> clinetsRes = new ArrayList<Client>();
		try {
			st = con.createStatement();

			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				String res_name = rs.getString("USER_NAME");
				String res_phone = rs.getString("USER_PH_NUM");
				String res_date = rs.getString("USER_REC_DATE");
				int res_loyalty = rs.getInt("USER_LOYALTY");

				clinetsRes.add(new Client(res_name, res_phone, res_date, res_loyalty));
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clinetsRes;
	}

	@Override
	public User getUserByName(String name) {
		String query = "SELECT users.USER_PASSWORD , users.USER_POSS FROM libr.users where users.USER_NAME = '"+ name + "' ;";

		Connection con = DBConnector.getConnection();
		Statement st;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(query);		
			if (rs.next()) {				
				return new User(name,rs.getString("USER_PASSWORD"), User.convertIntToPoss(rs.getInt("USER_POSS")));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
