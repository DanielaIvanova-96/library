package tuv.lib.models;

import javax.persistence.Entity;

import tuv.lib.models.User.Possition;

//@Entity
public class Operator {
	
	public Client createClient(String name, String pass, String recordDate,String phoneNum)
	{
		Client cl = new Client (name,pass,recordDate,phoneNum);
		return cl;
	}

}
