package org.vtiger.practice;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;	

public class FetchTheDataFromExcel {

	public static void main(String[] args) throws IOException {
		
		DataFormatter dataFormat=new DataFormatter();
		
		FileInputStream fisExcel=new FileInputStream("./src/test/resources/Book1.xlsx");
	//	Properties properties=new Properties();
	//	properties.load(fis);
		Workbook workbook = WorkbookFactory.create(fisExcel);
		Sheet sheet = workbook.getSheet("Contacts");
		
		for(int i=0; i<=sheet.getLastRowNum(); i++) {
		String data =dataFormat.formatCellValue(sheet.getRow(i).getCell(3));
		if(data.equalsIgnoreCase("ContactName")) {
			System.out.println(sheet.getRow(i+1).getCell(3));
			break;
		}
		}
		workbook.close();
		
	

	}

}
