package tuv.lib.models;

import javax.persistence.Entity;

import tuv.lib.models.User.Possition;

//@Entity
public class Admin extends User {
	

	/**
	 * Creates user with position operator.
	 * 
	 * @param name operator name
	 * @param pass operator password
	 * @return new operator
	 */
	public Admin(User u)
	{
		this.name = u.getName();
		this.possition = Possition.ADMIN;
	}
	
	
	
	public User createOperator(String name,String pass)
	{
		User u = new User(name,pass,Possition.OPERATOR);
		return u;
	}

}
