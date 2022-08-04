package org.vtiger.practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WriteDataBackToExcel {
	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		FileInputStream fis=new FileInputStream("./src/test/resources/Book2.xlsx");
		Workbook workbook = WorkbookFactory.create(fis);
		Sheet sheet= workbook.getSheet("Sheet1");
	Row row = sheet.getRow(1);
	Cell cell = row.createCell(100);
	cell.setCellValue("hari");
	
	FileOutputStream fos=new FileOutputStream("./src/test/resources/Book1.xlsx");
	workbook.write(fos);
	System.out.println("data is written back to excel");
	workbook.close();
		

	}

}
