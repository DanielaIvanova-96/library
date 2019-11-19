package tuv.lib.models.interfaces;

import tuv.lib.models.Rent;

public interface RentService {

	public int makeRent(Rent r);
	public int closeRent(Rent r);
}
