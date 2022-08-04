package org.vtiger.practice;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class FetchTheDataFromCsv {

	public static void main(String[] args) throws IOException, CsvException {
	CSVReader reader=new CSVReader(new FileReader("./src/test/resources/BookC.csv"));
	        List<String[]> allData = reader.readAll();
	        String[] dataLine = allData.get(4);
	       String data = dataLine[2];
	       System.out.println(data);

	}

}
