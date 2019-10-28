package tuv.lib.models.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
		//INSERT INTO libr.users (USER_NAME, USER_PASSWORD, USER_POSS)
		//VALUES ('Logi', 'pass5', '1');
		
		String query = "INSERT INTO libr.users (USER_NAME, USER_PASSWORD, USER_POSS)\r\n" + 
				"VALUES ('"+u.getName()+"', '"+u.getPassword()+"', '"+u.getPosstion().ordinal()+"');" ;
		
		Connection con = DBConnector.getConnection();
		ResultSet rs;
		
		try {
			Statement st = con.createStatement();
			st.executeUpdate(query);
		} catch (SQLException e) {
			
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
