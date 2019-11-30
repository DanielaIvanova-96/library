package tuv.lib.models.dao;

import java.util.List;

import tuv.lib.models.Rent;

public interface RentDAO {

	public int makeRent(Rent r);

	public int closeRent(Rent r);
	public List<Rent> getNotifications();

}
