package org.vtiger.genericUtility;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;



/**
 * this class contains reusable methods for CSV and property file 
 * @author Hari
 *
 */

public final class PropertyFileUtility {

	

	/**
	 * This method is used to initialize the property file
	 * @param PropertyFilePath
	 */
	private FileInputStream fis;
	private Properties properties;
	
	public void initializePropertyFile(String propertyFileFath) {
		
		try {
			fis=new FileInputStream(propertyFileFath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 properties=new Properties();
		try {
			properties.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    


	/**
	 * This method is used to fetch the data from property file
	 * @param key
	 * @return
	 */

	public String getDataFromProperty(String key) {
		return properties.getProperty(key);
	}
	/**
	 * This method is used to fetch the data From CSV file
	 * @param csvFilePath
	 * @param rowNumber
	 * @param cellNumber
	 * @return
	 */

	public String getDataFromCSV(String csvFilePath, int rowNumber,int cellNumber) {
		CSVReader reader=null;

		String allData=null;
		try {
			reader=new CSVReader(new FileReader(csvFilePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}


		try {
			allData = reader.readAll().get(rowNumber)[cellNumber];
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CsvException e) {
			e.printStackTrace();
		}
		return allData;
	}

}
