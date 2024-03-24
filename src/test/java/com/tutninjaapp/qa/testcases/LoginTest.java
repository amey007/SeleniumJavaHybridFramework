package com.tutninjaapp.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutninjaapp.qa.base.Base;
import com.tutninjaapp.qa.pages.AccountPage;
import com.tutninjaapp.qa.pages.HomePage;
import com.tutninjaapp.qa.pages.LoginPage;
import com.tutninjaapp.qa.utils.Utilities;

public class LoginTest extends Base {
	
	public LoginTest() {
		super();
	}
	
	public WebDriver driver;
	LoginPage loginPage;
	AccountPage acctPage;
	
	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		loginPage = homePage.navigateToLoginPage();
	}
	

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority = 1, dataProvider = "validLoginCredentials")
	public void verifyLoginWithValidCredentials(String email, String password) {
		acctPage = loginPage.enterCredentialsToLogin(email, password);
		Assert.assertTrue(acctPage.getDisplayStatusForEditYourAcctInfoField(), "Verify your credentials and try again");
				
	}
	
	@DataProvider(name = "validLoginCredentials")
	public Object[][] supplyTestData() {
		Object [][] data = Utilities.getTestDataFromExcel("Login");
		return data;
	}
	
	@Test(priority = 2)
	public void verifyLoginWithInvalidCredentials() {
		
		loginPage.enterCredentialsToLogin(Utilities.generateTimeStamp(), dataProp.getProperty("invalidPassword"));
		
		String actualWarningMsg = loginPage.retrieveEmailPasswordNotMatchingWarningText();
		String expectedWarningMsg = dataProp.getProperty("noEmailWarningMsg");
		Assert.assertEquals(actualWarningMsg, expectedWarningMsg, "Actual msg does not match the expected msg");		
	}
	
	@Test(priority = 3)
	public void verifyLoginWithInvalidEmailAndValidPassword() {
		
		loginPage.enterCredentialsToLogin(Utilities.generateTimeStamp(), prop.getProperty("validPassword"));
		
		String actualWarningMsg = loginPage.retrieveEmailPasswordNotMatchingWarningText();
		String expectedWarningMsg = dataProp.getProperty("noEmailWarningMsg");
		Assert.assertEquals(actualWarningMsg, expectedWarningMsg, "Actual msg does not match the expected msg");
	}
	
	@Test(priority = 4)
	public void verifyLoginWithValidEmailAndInvalidPassword() {
		
		loginPage.enterCredentialsToLogin(prop.getProperty("validEmail"), dataProp.getProperty("invalidPassword"));
		
		String actualWarningMsg = loginPage.retrieveEmailPasswordNotMatchingWarningText();
		String expectedWarningMsg = dataProp.getProperty("noEmailWarningMsg");
		Assert.assertEquals(actualWarningMsg, expectedWarningMsg, "Actual msg does not match the expected msg");
	}
	
	@Test(priority = 5)
	public void verifyLoginWithEmptyCredentials() {
		
		loginPage.enterCredentialsToLogin("", "");
		
		String actualWarningMsg = loginPage.retrieveEmailPasswordNotMatchingWarningText();
		String expectedWarningMsg = dataProp.getProperty("noEmailWarningMsg");
		Assert.assertEquals(actualWarningMsg, expectedWarningMsg, "Actual msg does not match the expected msg");
	}	
}
