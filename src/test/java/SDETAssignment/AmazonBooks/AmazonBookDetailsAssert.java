package SDETAssignment.AmazonBooks;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test; 
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
 

public class AmazonBookDetailsAssert {
	public static WebDriver driver;
  

  @BeforeTest
  public void beforeTest() {
	 System.setProperty("webdriver.chrome.driver", "chromedriver");
	 
	  String baseURL="https://www.amazon.in/";
		driver=new ChromeDriver();
	  
		driver.get(baseURL);
		driver.manage().window().maximize();
		String strGivenWindowTitle="Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
		  String strWindowTitle=driver.getTitle();
		Assert.assertEquals(strGivenWindowTitle, strWindowTitle);
		
  }
  @Test(priority=0)
  public static void searchBar( )
	{
	  	String searchKeyword="data catalog";
		driver.findElement(By.xpath("//div//a//span[text()='Shop by']")).click();       
        driver.findElement(By.linkText("All Books")).click();
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("data catalog");
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(Keys.ENTER);
		String strFetchedKeyword=driver.findElement(By.xpath("(//div[@id='s-result-info-bar-content']//span[@id='editableBreadcrumbContent']//span)[1]")).getText().toString().replaceAll("\"", "");
		Assert.assertEquals(searchKeyword, strFetchedKeyword);
	}
  @Test(priority=1)
  public static void displayFirstSearchResult()
	{
	  	String expectedBookTitle="The Condensed Chemical Dictionary; A Reference Volume for All Requiring Quick Access to a Large Amount of Essential Data Regarding Chemicals, and ... Used in Manufacturing and Laboratory Work";
		String bookTitle=driver.findElement(By.xpath("(//div[@id='atfResults']//ul//li//a[contains(@class,'access') and @title]//h2)[1]")).getText().toString();
		Assert.assertEquals(expectedBookTitle, bookTitle);
		 
	}
  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

   

}
