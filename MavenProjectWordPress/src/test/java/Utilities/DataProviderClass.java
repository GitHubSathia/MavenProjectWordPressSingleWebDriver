package Utilities;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataProviderClass {
	@DataProvider(name="TestCaseSheetData")			
	public Object[][] getDataFromDataProvider(Method method) throws IOException{	
			String testMethod=method.getName();
			Object[][] objectdata=null;	
			Object[][] objectdata1=null;	
			System.out.println("Test method : "+testMethod);
		 	Sheet guru99Sheet=ExcelRead.readExcel(System.getProperty("user.dir")+"\\src\\test\\java\\","TestCase1.xlsx","TestCase");
		int rowCount = guru99Sheet.getLastRowNum()-guru99Sheet.getFirstRowNum();	
		int colCount=guru99Sheet.getRow(1).getLastCellNum();
		objectdata=new Object[rowCount][colCount];	
	    for (int i = 0; i < rowCount; i++) {		
			Row row = guru99Sheet.getRow(i+1);	
			for (int j = 0; j <colCount; j++) {	
				 objectdata[i][j] = row.getCell(j,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).toString().trim();
				 System.out.println(objectdata[i][j]);
			        }	
				 }
	 	for(int i=0;i<rowCount;i++) {
	 		if(objectdata[i][1].equals(testMethod)) {
	 			 objectdata1= new Object[1][colCount];
	 			for (int j = 0; j <colCount; j++) {	
					 objectdata1[0][j] = objectdata[i][j];
					 
				        }	
	 			 
	 		}
	 	}
	    
	  
		return objectdata1;
           
}
	
}
