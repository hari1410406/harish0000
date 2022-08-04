package org.vtiger.genericUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * This class contains reusable method for Excel
 * @author avula
 *
 */
public final class ExcelUtility {

	private Workbook workbook ;

	/**
	 * This method is used to initialize the Excel File
	 * @param excelFilePath
	 * 
	 */
	public void initializeExcelFile(String excelFilePath) {
		FileInputStream fisExcel=null;

		try {
			fisExcel=new FileInputStream(excelFilePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			workbook = WorkbookFactory.create(fisExcel);
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * This method is used to fetch the data from Excel File
	 * @param cellNumber
	 * @param rowNumber
	 * @param sheetName
	 * @return
	 */
	public String getExcelData(int rowNumber,int CellNumber,String sheetName)
	{
		Sheet sheet = workbook.getSheet(sheetName);
		return new DataFormatter().formatCellValue(sheet.getRow(rowNumber).getCell(CellNumber));
	}
	

	/**
	 * This method is used to Write the data into Excel
	 * @param sheetName
	 * @param rowNumber
	 * @param cellNumber
	 * @param value
	 */
	public void setDataIntoExcel(String sheetName, int rowNumber, int cellNumber,String value)
	{
		Sheet sheet = workbook.getSheet(sheetName);
		sheet.getRow(rowNumber).createCell(cellNumber).setCellValue(value);
		System.out.println("Data Entered into Excel");
	}

	/**
	 * This method is used to save the data from Excel File
	 * @param excelSavePath
	 */

	public void saveTheDataIntoExcel(String excelSavePath)
	{
		FileOutputStream fosExcxel=null;
		try {
			fosExcxel=new FileOutputStream(excelSavePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			workbook.write(fosExcxel);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method is used to close the Excel File
	 */

	public void closeWorkbook() {

		try {
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

