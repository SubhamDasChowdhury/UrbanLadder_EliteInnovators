package com.UrbanLadder.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Excelconfig {
	static String search[] = new String[10];
	static Workbook wb;
	static ExtentReports extents;

	//Function to Read the data values from Excel File 
	public String[] readExcel(ExtentReports extents) throws Exception {
		ExtentTest test = extents.createTest("Read from Excel #");
		File obj = new File(System.getProperty("user.dir")+"\\src\\main\\resources\\Read_Excel.xlsx");
		FileInputStream fis = new FileInputStream(obj);
		Workbook wb = new XSSFWorkbook(fis);
		Sheet sh = wb.getSheetAt(0);
		search[0] = sh.getRow(2).getCell(0).toString();
		search[1] = sh.getRow(3).getCell(0).toString();
		search[2] = sh.getRow(4).getCell(0).toString();
		search[3] = sh.getRow(8).getCell(1).toString();
		search[4] = sh.getRow(10).getCell(1).toString();
		search[5] = sh.getRow(11).getCell(1).toString();
		test.log(Status.INFO, "Read from Excel Successful");
		return search;
	}

	//Function to Write the data values of Bookshelves and Study Chairs retrieved from Web Page into Excel File 
	public void positive_testing_writeExcel(String n[], String p[], String emi[], int items, String sheetname, int sheetno, ExtentReports extents) throws Exception 
	{
		ExtentTest test = extents.createTest("Write to Excel");

		File obj = new File(System.getProperty("user.dir")+"\\src\\main\\resources\\Positive _Testing_WriteExcel.xlsx");
		
		if (sheetno > 0) {
			FileInputStream fis = new FileInputStream(obj);
			wb = new XSSFWorkbook(fis);
		} else {
			wb = new XSSFWorkbook();
		}

		Sheet[] sh = new Sheet[10];
		sh[sheetno] = wb.createSheet(sheetname);
		Row row[] = new Row[10];
		int rowcount = 1;

		row[0] = sh[sheetno].createRow(0);
		row[0].createCell(0).setCellValue("Name");
		row[0].createCell(1).setCellValue("Price");
		row[0].createCell(2).setCellValue("EMI");

		for (int i = 0; i < items; i++) {
			row[rowcount] = sh[sheetno].createRow(rowcount);

			for (int j = 0; j < items; j++) {
				if (j == 0)
					row[rowcount].createCell(j).setCellValue(n[i]);
				else if (j == 1)
					row[rowcount].createCell(j).setCellValue(p[i]);
				else if (j == 2)
					row[rowcount].createCell(j).setCellValue(emi[i]);
			}
			rowcount++;
		}

		FileOutputStream fos = new FileOutputStream(obj);
		wb.write(fos);
		fos.close();

		test.log(Status.INFO, "Write to Excel done Successfully...");
	}
	
	//Function to retrieve and Write the data values of Error message displayed while performing Negative Testing into Excel File 
	public void negative_testing_writeExcel(String data[], String sheetname, int sheetno, int items, ExtentReports extents) throws Exception 
	{
		ExtentTest test = extents.createTest("Write to Excel");

		File obj = new File(System.getProperty("user.dir")+"\\src\\main\\resources\\Negative _Testing_WriteExcel.xlsx");
		
		if (sheetno > 0) {
			FileInputStream fis = new FileInputStream(obj);
			wb = new XSSFWorkbook(fis);
		} else {
			wb = new XSSFWorkbook();
		}

		Sheet[] sh = new Sheet[10];
		sh[sheetno] = wb.createSheet(sheetname);
		Row row[] = new Row[10];
		int rowcount = 0;
				
		for(int i=0; i<=items; i++) 
		{
			row[rowcount] = sh[sheetno].createRow(rowcount);
			for (int j = 0; j < items; j++) {
					row[rowcount].createCell(j).setCellValue(data[i]);
				
			}
			rowcount+=2;
		}
		FileOutputStream fos = new FileOutputStream(obj);
		wb.write(fos);
		fos.close();
		
		test.log(Status.INFO, "Write to Excel done Successfully...");

	}
	

}
