package com.tutninjaapp.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {
	
	WebDriver driver;
	
	// PAGE OBJECTS
	
//	@FindBy( )
//	private WebElement ;
	
	@FindBy(linkText = "Edit your account information")
	private WebElement editYourAcctInfoField ;
	
	
	public AccountPage(WebDriver driver) {
		this.driver = driver;
		// Automaticallus initalizes the AccountPage(this) elements by linking Webelement to its locators 
		PageFactory.initElements(driver, this);
	}
	
	
	// ACTIONS
	
	public boolean getDisplayStatusForEditYourAcctInfoField() {
		return editYourAcctInfoField.isDisplayed();
	}

}
