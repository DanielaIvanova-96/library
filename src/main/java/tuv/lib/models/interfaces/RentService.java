package tuv.lib.models.interfaces;

import java.util.List;

import tuv.lib.models.Rent;

public interface RentService {

	public int makeRent(Rent r);
	public int closeRent(Rent r);
	public List<Rent> getNotifications();
}
