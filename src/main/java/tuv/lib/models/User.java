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
@Entity
@Table(name = "USERS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "USER_POSS", discriminatorType = DiscriminatorType.INTEGER)
public class User {

	public enum Possition {
		ADMIN, OPERATOR, CLIENT
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	int id;

	@Column(name = "USER_NAME")
	String name;

	@Column(name = "USER_PASSWORD")
	String password;

	@Column(name = "USER_POSS")
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
	

}
