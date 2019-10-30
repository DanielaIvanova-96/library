package tuv.lib;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tuv.lib.models.Admin;
import tuv.lib.models.DBConnector;
import tuv.lib.models.User;
import tuv.lib.models.User.Possition;

public class AdminTest {

	private Admin admin;
	@Before
	public void setUp() throws Exception {
		admin = new Admin();
	}

	@After
	public void tearDown() throws Exception {
		DBConnector.Disconnect();		
	}

	@Test
	public void test() {
		User u = admin.createuser("testname", "testpass");
		assertEquals(u.getPosstion(), Possition.OPERATOR);
	}

	
}
