package Utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Config.Configuration;
import Utilities.JypersionListerner;
import Utilities.Driver;


public class TestBaseClass {
	@BeforeMethod//(groups= {"Flow1","Flow2"})
	public void driverInitialize() {
		Driver.initialize();
		 Driver.instance.get(Configuration.URL.appURL);
			Driver.instance.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS) ;
			}
	
	
	@AfterMethod//(groups= {"Flow1","Flow2"})
	  public void driverCleanUp() throws IOException {
		
		  Driver.close();
		 
	}

	
	
	//@AfterSuite(groups= {"Flow1","Flow2"})

    public void tearDown(){

        sendPDFReportByGMail("b6269615@gmail.com", "abcdef1234%", "b6269615@gmail.com", "PDF Report", "");

        }
	public static void takeSnapshot(WebDriver driver,String fileWithPath) throws Exception{
		 //Convert web driver object to TakeScreenshot
		
		TakesScreenshot scrshot=((TakesScreenshot)driver);
		 //Call getScreenshotAs method to create image file
		File SrcFile=scrshot.getScreenshotAs(OutputType.FILE);
		//Move image file to new destination
		File DestFile=new File(fileWithPath);
		//Copy file at destination
		FileHandler.copy(SrcFile, DestFile);

	}
	
	
	
	 private static void sendPDFReportByGMail(String from, String pass, String to, String subject, String body) {

		 Properties props = System.getProperties();

		 String host = "smtp.gmail.com";

		 props.put("mail.smtp.starttls.enable", "true");

		 props.put("mail.smtp.host", host);

		 props.put("mail.smtp.user", from);

		 props.put("mail.smtp.password", pass);

		 props.put("mail.smtp.port", "587");

		 props.put("mail.smtp.auth", "true");

		 Session session = Session.getDefaultInstance(props);

		 MimeMessage message = new MimeMessage(session);

		 try {

		     //Set from address

		 message.setFrom(new InternetAddress(from));

		 message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

		 //Set subject

		 message.setSubject(subject);

		 message.setText(body);

		 BodyPart objMessageBodyPart = new MimeBodyPart();

		 objMessageBodyPart.setText("Please Find The Attached Report File!");

		 Multipart multipart = new MimeMultipart();

		 multipart.addBodyPart(objMessageBodyPart);

		 objMessageBodyPart = new MimeBodyPart();

		 //Set path to the pdf report file

		 String filename = System.getProperty("user.dir")+"\\Default test.pdf";
		

		 //Create data source to attach the file in mail

		 DataSource source = new FileDataSource(filename);
		
		 objMessageBodyPart.setDataHandler(new DataHandler(source));
		
		 objMessageBodyPart.setFileName(filename);
		
		 multipart.addBodyPart(objMessageBodyPart);
		 
		/* String filename1 = System.getProperty("user.dir")+"\\src\\test\\java\\"+"TestResults.xlsx";
		 DataSource source1 = new FileDataSource(filename1);
		 BodyPart objMessageBodyPart1 = new MimeBodyPart();
		 objMessageBodyPart.setText("Please Find The Attached Report File!");
		 objMessageBodyPart1.setDataHandler(new DataHandler(source1));
		 objMessageBodyPart1.setFileName(filename1);
		 multipart.addBodyPart(objMessageBodyPart1);*/
		 

		 message.setContent(multipart);

		 Transport transport = session.getTransport("smtp");

		 transport.connect(host, from, pass);

		 transport.sendMessage(message, message.getAllRecipients());

		 transport.close();

		 }

		 catch (AddressException ae) {

		 ae.printStackTrace();

		 }

		 catch (MessagingException me) {

		 me.printStackTrace();

		 }

		 }
	
}
