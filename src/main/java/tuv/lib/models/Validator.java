package tuv.lib.models;

/**
 * Validates fields 
 * @author Zheni
 *
 */
public class Validator {

	/**
	 * Check if string is null or empty 
	 * @param str string checked
	 * @return
	 */
	public static boolean isNullOrEmpty(String str)
	{
		   if(str == null || str.isEmpty())
	            return true;
	        return false;
	}
	
}
