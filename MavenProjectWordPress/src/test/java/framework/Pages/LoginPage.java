package framework.Pages;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.log4testng.Logger;

import Config.Configuration;
import Utilities.Driver;
import Utilities.ExcelRead;
import Utilities.TestBaseClass;

public class LoginPage extends TestBaseClass {
	
	@FindBy(id="user_login")
	public static WebElement loginPageUserName;
	@FindBy(id="user_pass")
	public static WebElement loginPagePassword;
	@FindBy(id="wp-submit")
	public static WebElement loginPageLogIn;
	//@FindBy(id="login_error")
	//public static WebElement loginPageError;
	
	public LoginPage() {
		
		//PageFactory.initElements(Driver.instance, this);
	}
	
	/*public static void goTo() {
		  
		  Driver.instance.get(Configuration.URL.appURL);
			Driver.instance.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS) ;
		    }*/
	
	public static String login(String UserName,String Password) throws Exception {
		String status = null;
				loginPageUserName.clear();
				 loginPageUserName.sendKeys(UserName);
				 loginPagePassword.sendKeys(Password);
				 loginPageLogIn.click();
				 List<WebElement> elements = Driver.instance.findElements(By.id("login_error"));
				 	 if(elements.size()>0){
							 String errorText=elements.get(0).getText();
									 System.out.println("Login Failed"+errorText);
									 status="Pass"; 
							 } else {
							    System.out.println("Error not displayed");
							    
							 }	 
						return status;
			
		}
 
  		
	}
  

