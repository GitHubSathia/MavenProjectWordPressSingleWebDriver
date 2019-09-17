package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelRead {
	
	public static Sheet readExcel(String filePath,String fileName,String sheetName) throws IOException
	{
		File file=new File(filePath+"\\"+fileName);
		FileInputStream inputStream=new FileInputStream(file);
		Workbook workbook=null;
		String fileExtension=fileName.substring(fileName.indexOf("."));
		if(fileExtension.equals(".xlsx")) {
			workbook=new XSSFWorkbook(inputStream);
			
		}else if(fileExtension.equals(".xls")) {
			workbook=new HSSFWorkbook(inputStream);
			
		}
		
		Sheet sheet=workbook.getSheet(sheetName);
		
		return sheet;
		
	}
	public static void writeExcel(String filePath,String fileName,String sheetName,String status,String TCName) throws IOException
	{
		File file=new File(filePath+"\\"+fileName);
		FileInputStream inputStream=new FileInputStream(file);
		Workbook workbook=null;
		String fileExtension=fileName.substring(fileName.indexOf("."));
		if(fileExtension.equals(".xlsx")) {
			workbook=new XSSFWorkbook(inputStream);
			
		}else if(fileExtension.equals(".xls")) {
			workbook=new HSSFWorkbook(inputStream);
			
		}
		
		Sheet sheet=workbook.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();	
		int colCount=sheet.getRow(1).getLastCellNum();
		 for (int i = 0; i < rowCount; i++) {		
				Row row = sheet.getRow(i+1);	
				if(row.getCell(1).toString().trim().equalsIgnoreCase(TCName)) {
					Cell cell=row.getCell(5);
					cell.setCellValue(status);
					
			    }
		   
	         }
		 inputStream.close();		
		 FileOutputStream outputStream = new FileOutputStream(file);		
		 workbook.write(outputStream);		
		outputStream.close();		
	
		
	}
	
	
	
	
}
