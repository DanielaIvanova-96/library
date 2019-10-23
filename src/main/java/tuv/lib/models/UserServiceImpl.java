package tuv.lib.models;

import tuv.lib.models.dao.UserDAO;
import tuv.lib.models.interfaces.UserService;

public class UserServiceImpl implements UserService {

	private UserDAO userDAO;
	
	public void addUser(User u) {
		this.userDAO.addUser(u);
		
	}

	public void removeUser(int id) {
		this.userDAO.removeUser(id);
		
	}

}
