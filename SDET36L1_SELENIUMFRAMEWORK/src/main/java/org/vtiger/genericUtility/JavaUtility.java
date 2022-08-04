package org.vtiger.genericUtility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * This class contains java reusable methods
 * @author avula
 *
 */

public final class JavaUtility {
	/**
	 * This method is used to generate the Random number
	 * @return
	 */
	public int getRandomNumber() {
		return new Random().nextInt(1000);
		
	}
	/**
	 * This method is used to generate the Random number within limit
	 * @param limit
	 * @return
	 */
	
	public int getRandomNumber(int limit) {
		return new Random().nextInt(limit);
	}
	/**
	 * This method is used to convert String to Long data type
	 * @param stringData
	 * @return
	 */
	public long convertStringToLong(String stringData) {
		return Long.parseLong(stringData);
	}
	
	/**
	 * This method is used to get the current date in specific strategy
	 * @param strategy
	 * @return 
	 */
	
	public String getCurrentDate(String strategy) {
		return new SimpleDateFormat(strategy).format(new Date());
	}
	
	/**
	 * This method is used to split the String based on strategy
	 * @param value
	 * @param strategy
	 * @return
	 */
	
	public String[] splitString(String value, String strategy) {
		return value.split(strategy);
	}
	/**
	 * 
	 * @param value
	 */
	public void printStatement(String value) {
		System.out.println(value);
	}

}
