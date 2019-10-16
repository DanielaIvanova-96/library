package tuv.lib.models.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import tuv.lib.models.User;

public class UserDAOImpl implements UserDAO {

	private SessionFactory sessionFactory;
	
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	public User getUserById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		User u = (User) session.load(User.class, new Integer(id));
		return u;
	}

}
