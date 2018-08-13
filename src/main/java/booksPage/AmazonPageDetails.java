package booksPage;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class AmazonPageDetails {
	
	public static WebDriver driver;
	
	/*Method for opening web browser*/
	public static void openBrowser(String baseURL)
	{
		System.setProperty("webdriver.chrome.driver", "chromedriver");		 
		driver=new ChromeDriver();		 
		driver.get(baseURL);
		driver.manage().window().maximize();
	}
	
	/*Method for searching for an item. Takes search category and keyword as argument*/
	public static void searchBar(String searchCategory,String searchKeyword)
	{
		driver.findElement(By.xpath("//div//a//span[text()='Shop by']")).click();    
		
		//Link Text used as locator to identify and click the 'All Books' category
        driver.findElement(By.linkText(searchCategory)).click();        
        //ID used as Web Element locator for searching the keyword 'Data Catalog'
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(searchKeyword);
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(Keys.ENTER);
	}
	
	/*Method for displaying the first search result for 'Data Catalog' search under category Book*/
	public static void displayFirstSearchResult()
	{
		String bookTitle=driver.findElement(By.xpath("(//div[@id='atfResults']//ul//li//a[contains(@class,'access') and @title]//h2)[1]")).getText().toString();		
		System.out.println("Book Title "+bookTitle);
		displayAllAvailableBookDetails(bookTitle);
	}
	
	/*Method for displaying all the details available for a given book. Takes title of the book as argument*/
	public static void displayAllAvailableBookDetails(String bookTitle)
	{
		//Generic x-path used which selects all the details of a given book and stores in a List of Web ELements. The argument provided is the boo title which can be used in the x-path
		List<WebElement> wbBookDetails=driver.findElements(By.xpath("//div[@id='atfResults']//ul//li//a[contains(@class,'access') and @title]//h2[contains(text(),'"+bookTitle+"')]/../../../following-sibling::div/div/div"));
		String strDetailAvailable="";
		for(int i=0;i<wbBookDetails.size();i++)
		{
			driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);		 
			
			//String array used to store common keyword contained in the search result for any given book
			String[] strBookDetailsKeywords={"You Save","Get it by","More Buying Choices"};
			
			//Variable resultValueAvailability contains the values fetched from the web page containing the book details
			String resultValueAvailability=wbBookDetails.get(i).getText().toString().trim();
			
			/*Verifying whether a given search contains the expected keywords "You Save","Get it by","More Buying Choices"*/
			for(String strValue:strBookDetailsKeywords)
			{
				resultValueAvailability=resultValueAvailability.contains(strValue)?strValue:resultValueAvailability;
			 
			}
			
			/*Switch-case statement used to traverse through the result and print the relevant details about the book if applicable*/
			switch (resultValueAvailability) {
			case "Paperback":String strExisitingPrice="";
							 String strRevisedPrice="";
							String[] PriceAvailable= wbBookDetails.get(i+1).getText().toString().trim().split(" ");
							System.out.println("Revised Price is "+PriceAvailable[0]);
							System.out.println("Exisiting Price is "+PriceAvailable[2]);
				System.out.println("Paperback Price is ["+wbBookDetails.get(i+1).getText().toString().trim()+"]");
				
				break;
			case "You Save":strDetailAvailable=wbBookDetails.get(i).getText().toString().replaceAll("You Save:", "");
				System.out.println("Savings ["+strDetailAvailable.trim()+"]");
				break;
			case "Get it by":strDetailAvailable=wbBookDetails.get(i).getText().toString().replaceAll("Get it by", "");
				System.out.println("Delivery Date ["+strDetailAvailable.trim()+"]");
				break;
			case "Hardcover":System.out.println("Hardback Price ["+wbBookDetails.get(i+1).getText().toString().trim()+"]");
				break;
			case "More Buying Choices":strDetailAvailable=wbBookDetails.get(i).getText().toString().replaceAll("Get it by", "");
				System.out.println("More Buying Choices  ["+strDetailAvailable.trim()+"]");
				break;
			 
			}
		}
	}
	

}
