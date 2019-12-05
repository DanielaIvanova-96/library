package tuv.lib.models;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;


// could it be mapped super class -> admin , operator and client becomes entity
//@Entity
//@Table(name = "USERS")
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "USER_POSS", discriminatorType = DiscriminatorType.INTEGER)
/**Class for the client model
 * @author Zheni
 *
 */
public class User {

	/** 
	 * Enumeration for the user position
	 * @author Zheni
	 */
	public enum Possition {
		ADMIN, OPERATOR, CLIENT
	}

	int id;

	String name;

	String password;

	Possition possition;

	public User() {

	}

	public User(String name, String pass, Possition pos) {
		this.name = name;
		this.password = pass;
		this.possition = pos;		
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String pass) {
		this.password = pass;
	}
	
	public Possition getPosstion() {
		return possition;
	}

	public void setPosstion(Possition pos) {
		this.possition = pos;
	}
	
	/**Converts the integer value to the corresponding position
	 * @param possInt
	 * @return
	 * @throws Exception if the integer value does not have corresponding position
	 */
	public static Possition convertIntToPoss(int possInt) throws Exception 
	{
		switch (possInt) {
		case 0:
			return Possition.ADMIN;
		case 1:
			return Possition.OPERATOR;
		case 2:
			return Possition.CLIENT;
		default:
			throw new Exception("Integer value not supported");
		}
	}
	
	

}
