package org.vtiger.practice;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.Test;

public class ExcelMltipleDataProvider {
	
	@Test
	public void getMiltipleDataFromExcel() throws EncryptedDocumentException, IOException {
		FileInputStream fis=new FileInputStream("./src/test/resources/Book2.xlsx");
		Workbook book = WorkbookFactory.create(fis);
		Sheet sheet = book.getSheet("Dataprovider");
		int noofRows = sheet.getPhysicalNumberOfRows();
		int noofColumns = sheet.getRow(0).getLastCellNum();
		System.out.println(noofRows+"===>"+noofColumns);
		
		
		String[][] data=new String[noofRows][noofColumns];
		for (int i = 1; i <noofRows; i++) {
			for(int j=0; j<noofColumns; j++) {
				DataFormatter dataFormatter=new DataFormatter();
		//	dataFormatter.formatCellValue(sheet.getRow(i).getCell(j));
			System.out.println(dataFormatter.formatCellValue(sheet.getRow(i).getCell(j)));
			}
			
			
		}
	}

}