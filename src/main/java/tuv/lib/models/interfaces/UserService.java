package tuv.lib.models.interfaces;

import tuv.lib.models.User;

public interface UserService {
	public void addUser(User u);
	public void removeUser(int id);
	public int getUserPos(String name, String password);
}
