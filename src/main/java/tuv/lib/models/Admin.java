package tuv.lib.models;

import javax.persistence.Entity;

//@Entity
public class Admin extends User {
	
	public User createuser(String name,String pass)
	{
		User u = new User(name,pass,Possition.OPERATOR);
		return u;
	}

}
