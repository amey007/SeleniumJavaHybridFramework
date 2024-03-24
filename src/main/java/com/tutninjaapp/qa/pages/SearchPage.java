package com.tutninjaapp.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
	
	WebDriver driver;
	
	// PAGE OBJECTS
		
	@FindBy(linkText = "HP LP3065")
	private WebElement validProduct;
	
	@FindBy(xpath = "//input[@name='search']//following-sibling::span/button")
	private WebElement searchBtn;
	
	@FindBy(xpath = "//div[@id = 'content']/h2//following-sibling::p")
	private WebElement noProductFoundHeaderField;
	
	
	public SearchPage(WebDriver driver) {
		this.driver = driver;
		// Automaticallus initalizes the SearchPage(this) elements by linking Webelement to its locators 
		PageFactory.initElements(driver, this);
	}
	
	
	// ACTIONS
	
	public boolean getDisplayStatusOfValidProductOnSearchResultsPage() {
		return validProduct.isDisplayed();
	}
	
	public void clickOnSearchBtn() {
		searchBtn.click();
	}
	
	public String retrieveNoProductFoundHeaderFieldText() {
		return noProductFoundHeaderField.getText();
	}
}
