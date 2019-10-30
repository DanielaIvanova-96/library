package tuv.lib.models;

import java.sql.SQLException;

import tuv.lib.models.dao.UserDAO;
import tuv.lib.models.dao.UserDAOImpl;
import tuv.lib.models.interfaces.UserService;

public class UserServiceImpl implements UserService {

	private static UserDAO userDAO;
		
	public UserServiceImpl()
	{
		userDAO = new UserDAOImpl();
		
	}
	
	public void addUser(User u) {
		this.userDAO.addUser(u);		
	}

	public void removeUser(int id) {
		this.userDAO.removeUser(id);		
	}
	
	public int getUserPos(String name, String password)
	{
		try {
			return this.userDAO.getUserPos(name, password);			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;		
	}

}
