package SDETAssignment.AmazonBooks;


import booksPage.AmazonPageDetails;
import booksPage.AmazonPageDetails;
public class BookDetailsPage extends AmazonPageDetails {
public static void main(String[] args) throws Exception {		
		
		String baseURL="https://www.amazon.in/";
		String searchKeyword="data catalog";
		String searchCategory="All Books";
		AmazonPageDetails.openBrowser(baseURL);
		AmazonPageDetails.searchBar(searchCategory,searchKeyword);
		AmazonPageDetails.displayFirstSearchResult();
		 driver.quit();
	}
}
