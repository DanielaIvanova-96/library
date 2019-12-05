package tuv.lib.models;

import java.time.LocalDate;

import javax.persistence.Entity;

import tuv.lib.models.User.Possition;

//@Entity
/**Class for the operator model
 * @author Zheni
 *
 */
public class Operator extends User {

	public Operator(User u) {
		this.name = u.getName();
		this.possition = Possition.OPERATOR;
	}

	/**Creates client entity
	 * @param name
	 * @param pass
	 * @param recordDate
	 * @param phoneNum
	 * @return
	 */
	public Client createClient(String name, String pass, String recordDate, String phoneNum) {
		Client cl = new Client(name, pass, recordDate, phoneNum);
		return cl;
	}
	
}
