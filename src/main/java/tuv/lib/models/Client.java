package tuv.lib.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

//
//@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorValue ("2")
public class Client extends User {

	// @Column(name = "USER_LOYALTY")
	int loyalty;

	// @Column(name = "USER_PH_NUM")
	String phoneNum;

	// @Column(name = "USER_REC_DATE")
	LocalDate recordDate;

	public Client(String name, String pass, String recordDate, String phone) {
		super(name, pass, Possition.CLIENT);
		this.phoneNum = phone;
		this.loyalty = 0;
		this.recordDate = LocalDate.now();
	}

	public Client(String name, String phone, String recordDate, int loyalty) {
		this.name = name;
		this.phoneNum = phone;
		this.loyalty = loyalty;
		this.recordDate = LocalDate.parse(recordDate);
	}

	public Client(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return super.getName();
	}

	public int getLoyalty() {
		return loyalty;
	}

	public void setLoyalty(int loynalty) {
		this.loyalty = loynalty;
	}

	
	public String getPhoneNum() {
		return phoneNum;
	}

	@Override
	public String toString() {
		return this.name;
	}

	public String getRecordDate() {
		return recordDate.toString();
	}
}
