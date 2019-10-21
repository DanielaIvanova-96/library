package tuv.lib.models.dao;

import java.util.List;

import tuv.lib.models.User;

public interface UserDAO {
	public User getUserById(int id);
	public void addUser(User u);
	public void updateUser(User u);
	public void removeUser(int id);
	public List<User> listUsers();
}
