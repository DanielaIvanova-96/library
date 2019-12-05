package tuv.lib.models;

import javax.persistence.Entity;

import tuv.lib.models.User.Possition;

//@Entity
/**
 * Admin model that is used in the admin controller
 * @author Zheni
 *
 */
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
	
	/**Creates a operator
	 * @param name operators name
	 * @param pass operators password
	 * @return the generate operator
	 */
	public User createOperator(String name,String pass)
	{
		User u = new User(name,pass,Possition.OPERATOR);
		return u;
	}

}
