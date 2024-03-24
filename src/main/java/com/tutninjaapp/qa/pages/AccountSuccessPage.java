package com.tutninjaapp.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountSuccessPage {
	
WebDriver driver;
	
	// PAGE OBJECTS
	
//	@FindBy( )
//	private WebElement ;
	
	@FindBy(xpath = "//div[@id = 'content']/h1")
	private WebElement accountSuccessfullyCreatedHeader;
	
	
	public AccountSuccessPage(WebDriver driver) {
		this.driver = driver;
		// Automaticallus initalizes the AccountSuccessPage(this) elements by linking Webelement to its locators 
		PageFactory.initElements(driver, this);
	}
	
	
	// ACTIONS
	
	public String retrieveTextForAccountSuccessfullyCreatedHeader() {
		return accountSuccessfullyCreatedHeader.getText();
	}

}
