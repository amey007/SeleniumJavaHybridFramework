package com.tutninjaapp.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	

	WebDriver driver;
	
	// PAGE OBJECTS
	
//	@FindBy( )
//	private WebElement ;
	
	@FindBy(id = "input-email")
	private WebElement userEmail;
	
	@FindBy(id = "input-password" )
	private WebElement userPassword;
	
	@FindBy(xpath = "//input[@value = 'Login']")
	private WebElement loginBtn;
	
	@FindBy(xpath = "//div[contains(@class, 'alert-dismissible')]")
	private WebElement emailPasswordNotMatchingWarning;
	
	
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		// Automaticallus initalizes the LoginPage(this) elements by linking Webelement to its locators 
		PageFactory.initElements(driver, this);
	}
	
	
	// ACTIONS
	
	public void enterEmailAddress(String email) {
		userEmail.sendKeys(email);
	}
	
	public void enterPassword(String password) {
		userPassword.sendKeys(password);
	}
	
	public AccountPage clickLogin() {
		loginBtn.click();
		return new AccountPage(driver);
	}
	
	public String retrieveEmailPasswordNotMatchingWarningText() {
		return emailPasswordNotMatchingWarning.getText();
	}
	
	public AccountPage enterCredentialsToLogin(String emailText, String passwordText) {
		enterEmailAddress(emailText);
		enterPassword(passwordText);
		return clickLogin();
	}


}
