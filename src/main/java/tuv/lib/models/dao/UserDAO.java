package tuv.lib.models.dao;

import java.sql.SQLException;
import java.util.List;

import tuv.lib.models.Client;
import tuv.lib.models.User;

public interface UserDAO {
	public User getUserById(int id);
	public void addUser(User u);
	public void addClient(Client cl);
	public void updateUser(User u);
	public void removeUser(int id);
	public List<User> listUsers();
	public List<Client> findClients(String name);
	public List<Client> classifyClients(String classProperty);
	public int getUserPos(String name,String password) throws SQLException ;
}
