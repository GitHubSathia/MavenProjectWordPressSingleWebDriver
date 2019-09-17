package Utilities;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import Config.Configuration;
//Creating common singleton driver.At any point of time only one instance of driver will be there
public class Driver {
	public static WebDriver instance=null;
	
		
	 public static void initialize() {
	   if (instance==null)
	   {
		   System.out.println("Initializing Browser");
		   if(Configuration.Browser.browser.equalsIgnoreCase("ff")) {
			   System.setProperty("webdriver.gecko.driver", "C:\\Prabhu\\Personal\\KnowledgeBase\\Learning\\Selenium\\BrowserDriverDownloads\\geckodriver-v0.23.0-win64\\geckodriver.exe");
			   instance=new FirefoxDriver();
		   }else if(Configuration.Browser.browser.equalsIgnoreCase("chrome")) {
			   System.setProperty("webdriver.chrome.driver", "C:\\Prabhu\\Personal\\KnowledgeBase\\Learning\\Selenium\\BrowserDriverDownloads\\geckodriver-v0.23.0-win64\\geckodriver.exe");
				  instance=new ChromeDriver();
		   }else if(Configuration.Browser.browser.equalsIgnoreCase("ie")) {
			   System.setProperty("webdriver.ie.driver", "C:\\Prabhu\\Personal\\KnowledgeBase\\Learning\\Selenium\\BrowserDriverDownloads\\IEDriverServer_x64_3.14.0\\IEDriverServer.exe");
				instance=new InternetExplorerDriver();
		   }
	   }
	   
	   instance.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	   instance.manage().window().maximize();
		   
   }
	
	public static void close() {
		   System.out.println("Closing browser");
		   instance.close();
		   instance=null;
	   }
	  
   /*public static void close() {
	   System.out.println("Closing browser");
	   instance.close();
	   instance=null;
   }*/
	
	public static void quit() {
		   System.out.println("Quitting browser");
		   instance.quit();
		   instance=null;
	   }
   
   /*public static void quit() {
	   System.out.println("Quitting browser");
	   instance.quit();
	   instance=null;
   }*/
   
}
