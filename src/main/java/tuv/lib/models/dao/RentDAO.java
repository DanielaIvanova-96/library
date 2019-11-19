package tuv.lib.models.dao;

import tuv.lib.models.Rent;

public interface RentDAO {

	public int makeRent(Rent r);

	public int closeRent(Rent r);

}
