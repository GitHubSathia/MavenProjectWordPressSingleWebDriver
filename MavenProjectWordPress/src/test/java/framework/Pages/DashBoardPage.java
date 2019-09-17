package framework.Pages;

import  org.testng.Assert;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.log4testng.Logger;

import Utilities.Driver;
import Utilities.TestBaseClass;
public class DashBoardPage {
@FindBy(xpath="//h1[contains(text(),'Dashboard')]")
public static WebElement dashboardText;

 public static String textVerify() throws Exception {
	 String status=null;
	 if (dashboardText.getText().contentEquals("Dashboard")) {
		 status="Pass";
	 }else {
		 status="Fail";
		 TestBaseClass.takeSnapshot(Driver.instance,"C:\\Prabhu\\Personal\\KnowledgeBase\\Learning\\Selenium\\SeleniumWorkSpace\\MavenProjectWordPress\\testcasefail.jpeg");
	 }
	return status;
	 
	
 }


}

