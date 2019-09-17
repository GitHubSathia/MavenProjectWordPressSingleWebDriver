package Tests;

import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;



import Utilities.JypersionListerner;
import Utilities.Driver;
import Utilities.ExcelRead;
import Utilities.TestBaseClass;
import framework.Pages.DashBoardPage;
import framework.Pages.LoginPage;
@Listeners(JypersionListerner.class)

public class LoginTests extends TestBaseClass {
	
	
  @Test(dataProvider="TestCaseSheetData",dataProviderClass=Utilities.DataProviderClass.class)
  public static void userCanLogin(String TCNo,String TCName,String UserName,String Password,String Credentials,String Status,String Title,String Block) throws Exception {
	  String userCanLoginstatus=null;
			  //LoginPage.goTo();
	  	    PageFactory.initElements(Driver.instance, LoginPage.class);
	 	   LoginPage.login(UserName,Password);
	 	  PageFactory.initElements(Driver.instance, DashBoardPage.class); 
	 	 userCanLoginstatus=DashBoardPage.textVerify();
	 	ExcelRead.writeExcel(System.getProperty("user.dir")+"\\src\\test\\java\\","TestCase1.xlsx","TestCase", userCanLoginstatus, TCName);
	  }
 @Test(dataProvider="TestCaseSheetData",dataProviderClass=Utilities.DataProviderClass.class)
  public static void userCannotLogin(String TCNo,String TCName,String UserName,String Password,String Credentials,String Status,String Title,String Block) throws Exception {
		 String userCannotLoginstatus=null;
	  		 // LoginPage.goTo();
	  	    PageFactory.initElements(Driver.instance, LoginPage.class);
	  	  userCannotLoginstatus=LoginPage.login(UserName,Password);
	 	   ExcelRead.writeExcel(System.getProperty("user.dir")+"\\src\\test\\java\\","TestCase1.xlsx","TestCase", userCannotLoginstatus, TCName);
	 	  
		 	  }
 }
 

