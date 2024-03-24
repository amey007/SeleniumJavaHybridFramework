package com.tutninjaapp.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutninjaapp.qa.base.Base;
import com.tutninjaapp.qa.pages.HomePage;
import com.tutninjaapp.qa.pages.SearchPage;

public class SearchTest extends Base{
	
	public SearchTest() {
		super();		
	}
	
	public WebDriver driver;
	SearchPage searchPage;
	HomePage homePage;
	
	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browserName"));
		homePage = new HomePage(driver);
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority = 1)
	public void verifySearchWithValidProduct() {
		searchPage = homePage.enterProductAndSearch(dataProp.getProperty("validSearchProduct"));
		Assert.assertTrue(searchPage.getDisplayStatusOfValidProductOnSearchResultsPage(), "Relevant products not displayed");
	}
	
	@Test(priority = 2)
	public void verifySearchWithInvalidProduct() {
		searchPage = homePage.enterProductAndSearch(dataProp.getProperty("invalidSearchProduct"));
		String actualMessage = searchPage.retrieveNoProductFoundHeaderFieldText();
		Assert.assertEquals(actualMessage,dataProp.getProperty("noProductDisplayedMsg"), "No product message is not dispalyed");
	}
	
	@Test(priority = 3, dependsOnMethods = {"verifySearchWithValidProduct","verifySearchWithInvalidProduct"})
	public void verifySearchWithoutAnyProduct() {
		searchPage = homePage.enterProductAndSearch("");
		String actualMessage = searchPage.retrieveNoProductFoundHeaderFieldText();
		Assert.assertEquals(actualMessage,dataProp.getProperty("noProductDisplayedMsg"), "No product message is not dispalyed");
	}
	
	

}
