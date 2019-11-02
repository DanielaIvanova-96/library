package tuv.lib.models;

import javax.persistence.Entity;

//@Entity
public class Admin extends User {
	

	/**
	 * Creates user with position operator.
	 * 
	 * @param name operator name
	 * @param pass operator password
	 * @return new operator
	 */
	public User createuser(String name,String pass)
	{
		User u = new User(name,pass,Possition.OPERATOR);
		return u;
	}

}
