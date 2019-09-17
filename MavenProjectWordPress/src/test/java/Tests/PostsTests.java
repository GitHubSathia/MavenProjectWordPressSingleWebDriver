package Tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import Config.Configuration;
import Utilities.Driver;
import Utilities.ExcelRead;
import Utilities.TestBaseClass;
import framework.Pages.LoginPage;
import framework.Pages.PostsPage;

public class PostsTests extends TestBaseClass {
  @Test(dataProvider="TestCaseSheetData",dataProviderClass=Utilities.DataProviderClass.class)
  public static void createPosts(String TCNo,String TCName,String UserName,String Password,String Credentials,String Status,String Title,String Block) throws Exception {
	  String createPostsStatus=null;
	    PageFactory.initElements(Driver.instance, LoginPage.class);
	   LoginPage.login(UserName,Password);
	  PageFactory.initElements(Driver.instance, PostsPage.class);
	  PostsPage.goToPosts();
	  createPostsStatus=PostsPage.addNewPost(Title,Block);
	  ExcelRead.writeExcel(System.getProperty("user.dir")+"\\src\\test\\java\\","TestCase1.xlsx","TestCase", createPostsStatus, TCName);
  }
  @Test(dataProvider="TestCaseSheetData",dataProviderClass=Utilities.DataProviderClass.class,dependsOnMethods={"createPosts"})
  public static void editPosts(String TCNo,String TCName,String UserName,String Password,String Credentials,String Status,String Title,String Block) throws Exception {
	  String editPostsStatus=null;
	    PageFactory.initElements(Driver.instance, LoginPage.class);
	   LoginPage.login(UserName,Password);
	  PageFactory.initElements(Driver.instance, PostsPage.class);
	  PostsPage.goToPosts();
	  editPostsStatus=PostsPage.modifyPost(Title,Block);
	  ExcelRead.writeExcel(System.getProperty("user.dir")+"\\src\\test\\java\\","TestCase1.xlsx","TestCase", editPostsStatus, TCName);
  }
  
}
