package tuv.lib.models;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Log4 {
	
	static Logger logger = LogManager.getLogger(Log4.class);

	public Log4()
	{
		BasicConfigurator.configure();
	}
	
	public static void logLogIn(User u) {
		logger.info("User "+u.getName()+" logged into the system.");
	}
}
