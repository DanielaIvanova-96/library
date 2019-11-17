package tuv.lib.models;

import java.sql.SQLException;
import java.util.List;

import tuv.lib.models.dao.UserDAO;
import tuv.lib.models.dao.UserDAOImpl;
import tuv.lib.models.interfaces.UserService;

/**
 * Service used in controllers for communication with the database for the user
 * models
 * 
 * @author Zheni
 */
public class UserServiceImpl implements UserService {

	private UserDAO userDAO;

	public UserServiceImpl() {
		userDAO = new UserDAOImpl();
	}

	public void addUser(User u) {
		this.userDAO.addUser(u);
	}

	public void addClient(Client cl) {
		this.userDAO.addClient(cl);
	}

	public void removeUser(int id) {
		this.userDAO.removeUser(id);
	}

	public int getUserPos(String name, String password) {
		try {
			return this.userDAO.getUserPos(name, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public List<Client> findClients(String name) {
		return this.userDAO.findClients(name);
	}

	public List<Client> classifyClients(String classProperty) {
		return this.userDAO.classifyClients(classProperty);
	}

}
