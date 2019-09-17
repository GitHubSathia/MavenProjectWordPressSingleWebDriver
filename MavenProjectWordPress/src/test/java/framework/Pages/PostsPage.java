package framework.Pages;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import Config.Configuration;
import Utilities.Driver;

public class PostsPage {
	//@FindBy(xpath="//div[@class='wp-menu-name' and @text()='Posts']")
	@FindBy(linkText="Posts")
	public static WebElement postsLink;
	@FindBy(xpath="//a[@href='post-new.php']")
	public static WebElement addNewPostsLink;
	@FindBy(id="post-title-0")
	public static WebElement titleTextBox;
	@FindBy(xpath="//textarea[text()='Start writing or type / to choose a block']")
	//@aria-label='Empty block; start writing or type forward slash to choose a block'
	public static WebElement blockTextBox;
	@FindBy(xpath="//div[contains(@class,'wp-block editor-block-list__block block-editor-block-list__block')]")
	public static WebElement blockTextBox1;
	@FindBy(xpath="//button[contains(@class,'components-button editor-post-publish')]")
	//@FindBy(partialLinkText="Publish")
	public static WebElement publishButton;
	@FindBy(xpath="//button[text()='Publish']")
	public static WebElement publishButton1;
	@FindBy(xpath="//div[@class='editor-post-publish-panel__header-published']")
	public static WebElement publishMessage;
	@FindBy(linkText="All Posts")
	public static WebElement allPostsLink;
	@FindBy(xpath="//p[contains(@class,'block-editor-rich-text__editable editor')]")
	public static WebElement editTextBox;
	@FindBy(xpath="//button[text()='Update']")
	public static WebElement updateButton;
	@FindBy(xpath="//div[@class='components-notice__content']")
	public static WebElement updateMessage;
	@FindBy(xpath="//table[@class='wp-list-table widefat fixed striped posts']/thead/tr/th")
	public static List<WebElement> col;
	@FindBy(xpath="//table[@class='wp-list-table widefat fixed striped posts']/tbody/tr")
	public static List<WebElement> row;
	//@FindBy(how=How.XPATH,using="//table[@class='wp-list-table widefat fixed striped posts']/tbody/tr[\" + (i+1)+ \"]/td[1]")
	//public static WebElement data1;
	@FindBy(xpath="//table[@class='wp-list-table widefat fixed striped posts']/tbody/tr[\" + (i+1)+ \"]/td[1]")
	public static WebElement data;
	@FindBy(xpath="//table[@class='wp-list-table widefat fixed striped posts']/tbody/tr[\" + (i+1)+ \"]/td[1]/div[3]/span[1]")
	public static WebElement edit;
	
	
	public static void goToPosts() throws Exception {
				 	  postsLink.click();
				 	 			}
	public static String addNewPost(String title,String block) throws InterruptedException {
		String status=null;
		addNewPostsLink.click();
		titleTextBox.sendKeys(title);
		blockTextBox.click();
		Actions keydown=new Actions(Driver.instance);
		keydown.moveToElement(blockTextBox1).sendKeys(blockTextBox1, block).build().perform();
		publishButton.click();
		publishButton1.click();
		if(publishMessage.getText().equalsIgnoreCase("Published")){
			status="Pass";
		}else {
			status="Fail";
		}
		return status;
		
	}
	
	
	public static String modifyPost(String title,String block) {
		String status=null;
		allPostsLink.click();
		//List<WebElement> col=Driver.instance.findElements(By.xpath("//table[@class='wp-list-table widefat fixed striped posts']/thead/tr/th"));
		//List<WebElement> row=Driver.instance.findElements(By.xpath("//table[@class='wp-list-table widefat fixed striped posts']/tbody/tr"));
		int colCount=col.size();
		int rowCount=row.size();
		/*for(int i=0;i<row.size();i++)
		{
			for(int j=0;j<col.size();j++)
			{
				String celltext=Driver.instance.findElement(By.xpath("//table[@class='wp-list-table widefat fixed striped posts']/tbody/tr[" + (i+1)+ "]/td[" + (j+1)+ "]")).getText();
				System.out.println("Row["+i+"]Col["+j+"]="+celltext);
			}
			System.out.println("---------");
		}*/
		for(int i=0;i<row.size();i++)
		{
				//WebElement data=Driver.instance.findElement(By.xpath("//table[@class='wp-list-table widefat fixed striped posts']/tbody/tr[" + (i+1)+ "]/td[1]"));
				String celltext=data.getText();
				if(celltext.equalsIgnoreCase(title)) {
					Actions mouseover=new Actions(Driver.instance);
					mouseover.moveToElement(data).build().perform();
					//WebElement edit=Driver.instance.findElement(By.xpath("//table[@class='wp-list-table widefat fixed striped posts']/tbody/tr[" + (i+1)+ "]/td[1]/div[3]/span[1]"));
					edit.click();
					editTextBox.clear();
					//WebElement editTextBox=Driver.instance.findElement(By.xpath("//p[contains(@class,'block-editor-rich-text__editable editor')]"));
					//editTextBox.clear();
					Actions keydown1=new Actions(Driver.instance);
					keydown1.moveToElement(editTextBox).sendKeys(editTextBox, block).build().perform();
					updateButton.click();
					System.out.println(updateMessage.getText());
					if(updateMessage.getText().contains("Post updated")) {
						status="Pass";
					}else {
						status="Fail";
					}
					break;
				}
		}
		
		return status;
		
	}
	
	
	
}
