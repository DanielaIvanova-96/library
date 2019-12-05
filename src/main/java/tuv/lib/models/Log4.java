package tuv.lib.models;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**Logger class that logs info
 * @author Zheni
 *
 */
public class Log4 {
	
	static Logger logger = LogManager.getLogger(Log4.class);

	public Log4()
	{
		BasicConfigurator.configure();
	}
	
	/**Logger logs who entered the system
	 * @param u user that loggs into the system
	 */
	public static void logLogIn(User u) {
		logger.info("User "+u.getName()+" logged into the system.");
	}
}
