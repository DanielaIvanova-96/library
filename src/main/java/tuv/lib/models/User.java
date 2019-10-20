package tuv.lib.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class User {

	enum Possition {
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

	@Column(name = "USER_LOYALTY")
	int loyalty;

	@Column(name = "USER_PH_NUM")
	String phoneNum;

	@Column(name = "USER_REC_DATE")
	LocalDate recordDate;

	public User() {

	}

	public User(String name, String pass, Possition pos, LocalDate recDate) {
		this.name = name;
		this.password = pass;
		this.possition = pos;
		this.recordDate = recDate;
	}

	public User(String name, String pass, Possition pos, int loyalty, String phNum, LocalDate recDate) {
		this.name = name;
		this.password = pass;
		this.possition = pos;
		this.loyalty = loyalty;
		this.phoneNum = phNum;
		this.recordDate = recDate;
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

}
