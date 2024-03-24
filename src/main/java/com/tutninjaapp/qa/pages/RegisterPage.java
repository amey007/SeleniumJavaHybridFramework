package com.tutninjaapp.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	
	WebDriver driver;
	
	// PAGE OBJECTS
	
	@FindBy(id = "input-firstname")
	private WebElement firstName;
	
	@FindBy(id = "input-lastname")
	private WebElement lastName;
	
	@FindBy(id = "input-email")
	private WebElement email;
	
	@FindBy(id = "input-telephone")
	private WebElement telephone;
	
	@FindBy(id = "input-password")
	private WebElement password;
	
	@FindBy(id = "input-confirm")
	private WebElement confirmPassword;
	
	@FindBy(name = "agree")
	private WebElement privacyPolicyCheckbox;
	
	@FindBy(xpath = "//input[contains(@value, 'Continue')]")
	private WebElement continueBtn;
	
	@FindBy(xpath = "//input[@name = 'newsletter'][@value = 1]")
	private WebElement subscribeNewsletterAsYes;
	
	@FindBy(xpath = "//div[contains(@class, 'alert-dismissible')]")
	private WebElement duplicateEmailAddressWarningField;
	
	@FindBy(xpath = "//div[contains(@class, 'alert-dismissible')]")
	private WebElement privacyPolicyWarning;
	
	@FindBy(xpath = "//input[@id = 'input-firstname']//following::div")
	private WebElement firstNameWarning;
	
	@FindBy(xpath = "//input[@id = 'input-lastname']//following::div")
	private WebElement lastNameWarning;
	
	@FindBy(xpath = "//input[@id = 'input-email']//following::div")
	private WebElement emailAddressWarning;
	
	@FindBy(xpath = "//input[@id = 'input-telephone']//following::div")
	private WebElement telephoneWarning;
	
	@FindBy(xpath = "//input[@id = 'input-password']//following::div")
	private WebElement passwordWarning;
	
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// ACTIONS
	public void enterFirstName(String firstNameText) {
		firstName.sendKeys(firstNameText);
	}
	
	public void enterLastName(String lastNameText) {
		lastName.sendKeys(lastNameText);
	}
	
	public void enterEmailAddress(String emailAddrText) {
		email.sendKeys(emailAddrText);
	}
	
	public void enterTelephoneNumber(String telephoneNumber) {
		telephone.sendKeys(telephoneNumber);
	}
	
	public void enterPassword(String passwordText) {
		password.sendKeys(passwordText);
	}
	
	public void enterConfirmPassword(String confirmPasswordText) {
		confirmPassword.sendKeys(confirmPasswordText);
	}
	
	public void clickOnPrivacyPolicyAgreement() {
		privacyPolicyCheckbox.click();
	}
	
	public AccountSuccessPage clickOnContinue() {
		continueBtn.click();
		return new AccountSuccessPage(driver);
	}
	
	public void selectYesNewsletterOptions() {
		subscribeNewsletterAsYes.click();
	}
	
	public String retriveDuplicateEmailAddressWarningMsg() {
		return duplicateEmailAddressWarningField.getText();
	}
	
	public String retrievePrivacyPolicyWarning() {
		return privacyPolicyWarning.getText();
	}
	
	public String retrieveFirstNameWarning() {
		return firstNameWarning.getText();
	}
	
	public String retrieveLastNameWarning() {
		return lastNameWarning.getText();
	}
	
	public String retrieveEmailAddressWarning() {
		return emailAddressWarning.getText();
	}
	
	public String retrieveTelephoneWarning() {
		return telephoneWarning.getText();
	}
	
	public String retrievePasswordWarning() {
		return passwordWarning.getText();
	}
	
	public AccountSuccessPage registerUser(String firstname, String lastname, 
			String email, String telnumber, String password, String option) {
		
		enterFirstName(firstname);
		enterLastName(lastname);
		enterEmailAddress(email);
		enterTelephoneNumber(telnumber);
		enterPassword(password);
		enterConfirmPassword(password);
		if (option.equalsIgnoreCase("Yes")){
			selectYesNewsletterOptions();
		}
		clickOnPrivacyPolicyAgreement();
		return clickOnContinue();
	}
	

}
