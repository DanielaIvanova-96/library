package tuv.lib.models;

import javax.persistence.Entity;

import tuv.lib.models.User.Possition;

//@Entity
public class Operator {
	
	public Client createClient(String name, String pass ,String phoneNum)
	{
		Client cl = new Client (name,pass,phoneNum);
		return cl;
	}

}
