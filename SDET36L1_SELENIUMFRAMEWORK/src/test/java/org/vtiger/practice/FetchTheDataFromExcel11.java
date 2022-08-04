package org.vtiger.practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FetchTheDataFromExcel11 {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		
		DataFormatter dataFormat=new DataFormatter();
		FileInputStream fis=new FileInputStream("./src/test/resources/Book1.xlsx");
		Workbook workbook = WorkbookFactory.create(fis);
Sheet sheet = workbook.getSheet("Contacts");
int rowNumber = sheet.getLastRowNum();
int cellNum = sheet.getRow(1).getLastCellNum();
String[][] str=new String[rowNumber][cellNum];
for(int i=1; i<=rowNumber;i++) {
	for(int j=0; j<cellNum; j++) {
		str[i-1][j]=dataFormat.formatCellValue(sheet.getRow(i).getCell(j));
	}
}
		
	System.out.println(str[3][1]);
	System.out.println("data fetched");
		workbook.close();
		

	}

}
