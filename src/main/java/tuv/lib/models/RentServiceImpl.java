package tuv.lib.models;

import tuv.lib.models.dao.RentDAO;
import tuv.lib.models.dao.RentDAOImpl;
import tuv.lib.models.interfaces.RentService;

public class RentServiceImpl implements RentService {
	private RentDAO rentDAO;

	public RentServiceImpl() {
		this.rentDAO = new RentDAOImpl();
	}

	public int makeRent(Rent r) {
		return this.rentDAO.makeRent(r);
	}

	public int closeRent(Rent r) {
		return this.rentDAO.closeRent(r);
	}

}
