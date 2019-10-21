package tuv.lib.models.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import tuv.lib.models.User;

public class UserDAOImpl implements UserDAO {

	private SessionFactory sessionFactory;
	
	
//	public void setSessionFactory(SessionFactory sf){
//		this.sessionFactory = sf;
//	}
	
	public User getUserById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		User u = (User) session.load(User.class, new Integer(id));
		return u;
	}

	public void addUser(User u) {
		Session session = this.sessionFactory.getCurrentSession();		
		session.persist(u);		
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

}
