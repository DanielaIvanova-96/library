package tuv.lib.models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.function.Consumer;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import tuv.lib.models.Client;
import tuv.lib.models.DBConnector;
import tuv.lib.models.User;

public class UserDAOImpl implements UserDAO {

	private SessionFactory sessionFactory;

	// public void setSessionFactory(SessionFactory sf){
	// this.sessionFactory = sf;
	// }

	public User getUserById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		User u = (User) session.load(User.class, new Integer(id));
		return u;
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
		Session session = this.sessionFactory.getCurrentSession();
		session.update(u);
	}

	public void removeUser(int id) {
		// TODO Auto-generated method stub

	}

	public List<User> listUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getUserPos(String name, String password) throws SQLException {
		String query = "SELECT USER_POSS\r\n" + "FROM libr.users\r\n" + "WHERE USER_NAME = \"" + name
				+ "\" AND USER_PASSWORD = \"" + password + "\"; ";

		Connection con = DBConnector.getConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);

		if (rs.next()) {
			return rs.getInt(1);
		}

		return -1;
	}

}
