package com.tutninjaapp.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutninjaapp.qa.base.Base;
import com.tutninjaapp.qa.pages.AccountSuccessPage;
import com.tutninjaapp.qa.pages.HomePage;
import com.tutninjaapp.qa.pages.RegisterPage;
import com.tutninjaapp.qa.utils.Utilities;

public class RegisterTest extends Base {
	
	public RegisterTest() {
		super();
	}
	
	public WebDriver driver;
	RegisterPage registerPage;
	AccountSuccessPage acctSuccessPage;
	
	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browserName"));		
		HomePage homePage = new HomePage(driver);
		registerPage = homePage.navigateToRegisterPage();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority = 1)
	public void verifyRegisterAccountWithMandatoryFields() {
		
		acctSuccessPage = registerPage.registerUser(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"),
				Utilities.generateTimeStamp(), dataProp.getProperty("telephone"),
				prop.getProperty("validPassword"), "");
//		registerPage.enterFirstName(dataProp.getProperty("firstName"));
//		registerPage.enterLastName(dataProp.getProperty("lastName"));
//		registerPage.enterEmailAddress(Utilities.generateTimeStamp());
//		registerPage.enterTelephoneNumber(dataProp.getProperty("telephone"));
//		registerPage.enterPassword(prop.getProperty("validPassword"));
//		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
//		registerPage.clickOnPrivacyPolicyAgreement();
//		acctSuccessPage = registerPage.clickOnContinue();
		
		String actualAcctCreationMsg = acctSuccessPage.retrieveTextForAccountSuccessfullyCreatedHeader();
		String expectedSuccessMsg = dataProp.getProperty("acctSuccessCreationMsg");
		Assert.assertEquals(actualAcctCreationMsg, expectedSuccessMsg, "Account Success Page is not displayed");
	}
	
	@Test(priority = 2)
	public void verifyRegisterAccountWithAllFields() {
		acctSuccessPage = registerPage.registerUser(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"),
				Utilities.generateTimeStamp(), dataProp.getProperty("telephone"),
				prop.getProperty("validPassword"), "Yes");
//		registerPage.enterFirstName(dataProp.getProperty("firstName"));
//		registerPage.enterLastName(dataProp.getProperty("lastName"));
//		registerPage.enterEmailAddress(Utilities.generateTimeStamp());
//		registerPage.enterTelephoneNumber(dataProp.getProperty("telephone"));
//		registerPage.enterPassword(prop.getProperty("validPassword"));
//		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
//		registerPage.selectYesNewsletterOptions();
//		registerPage.clickOnPrivacyPolicyAgreement();
//		acctSuccessPage = registerPage.clickOnContinue();
		
		String actualAcctCreationMsg = acctSuccessPage.retrieveTextForAccountSuccessfullyCreatedHeader();
		String expectedSuccessMsg = dataProp.getProperty("acctSuccessCreationMsg");
		Assert.assertEquals(actualAcctCreationMsg, expectedSuccessMsg, "Account Success Page is not displayed");
	}
	
	@Test(priority = 3)
	public void verifyRegisterAccountWithExistingEmailAddress() {
		
		registerPage.registerUser(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"),
				prop.getProperty("validEmail"), dataProp.getProperty("telephone"),
				prop.getProperty("validPassword"), "Yes");
//		registerPage.enterFirstName(dataProp.getProperty("firstName"));
//		registerPage.enterLastName(dataProp.getProperty("lastName"));
//		registerPage.enterEmailAddress(prop.getProperty("validEmail"));
//		registerPage.enterTelephoneNumber(dataProp.getProperty("telephone"));
//		registerPage.enterPassword(prop.getProperty("validPassword"));
//		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
//		registerPage.selectYesNewsletterOptions();
//		registerPage.clickOnPrivacyPolicyAgreement();
//		registerPage.clickOnContinue();
		
		String actualWarningMsg = registerPage.retriveDuplicateEmailAddressWarningMsg();
		String expectedWarningMsg = dataProp.getProperty("emailAlreadyRegisteredMsg");
		Assert.assertTrue(actualWarningMsg.contains(expectedWarningMsg), "Warning message for duplicate email is not present");
	
	}
	
	@Test(priority = 4)	
	public void verifyRegisterAccountWithoutFillingAnyDetails() {

		registerPage.clickOnContinue();
		
		String actualPolicyWarningMsg = registerPage.retrievePrivacyPolicyWarning();
		Assert.assertTrue(actualPolicyWarningMsg.contains(dataProp.getProperty("privacyPolicyWarning")), "Warning message for duplicate email is not present");
		
		String actualFirstNameWarningMsg = registerPage.retrieveFirstNameWarning();
		Assert.assertTrue(actualFirstNameWarningMsg.contains(dataProp.getProperty("firstNameWarning")), "Warning message is not present");
		
		String actualLastNameWarningMsg = registerPage.retrieveLastNameWarning();
		Assert.assertTrue(actualLastNameWarningMsg.contains(dataProp.getProperty("lastNameWarning")), "Warning message is not present");
		
		String actualEmailWarningMsg = registerPage.retrieveEmailAddressWarning();
		Assert.assertTrue(actualEmailWarningMsg.contains(dataProp.getProperty("EmailAddrWarning")), "Warning message is not present");
		
		String actualTelephoneWarningMsg = registerPage.retrieveTelephoneWarning();
		Assert.assertTrue(actualTelephoneWarningMsg.contains(dataProp.getProperty("telephoneWarning")), "Warning message is not present");
		
		String actualPasswordWarningMsg = registerPage.retrievePasswordWarning();
		Assert.assertTrue(actualPasswordWarningMsg.contains(dataProp.getProperty("passwordWarning")), "Warning message is not present");

	}
	

}
