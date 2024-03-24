package com.tutninjaapp.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	
	// PAGE OBJECTS
	
	@FindBy(xpath = "//span[text() = 'My Account']")
	private WebElement myAcctDropMenu;
	
	@FindBy(linkText = "Login")
	private WebElement loginOption;
	
	@FindBy(linkText = "Register")
	private WebElement registerOption;
	
	@FindBy(name = "search")
	private WebElement searchField;
	
	@FindBy(xpath = "//input[@name='search']//following-sibling::span/button")
	private WebElement searchBtn;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		// Automaticallus initalizes the HomePage(this) elements by linking Webelement to its locators 
		PageFactory.initElements(driver, this);
	}
	
	
	// ACTIONS
	
	public void clickOnMyAcct() {
		myAcctDropMenu.click();
	}
	
	public LoginPage selectLoginOption() {
		loginOption.click();
		return new LoginPage(driver);
	}
	
	public RegisterPage selectRegisterOption() {
		registerOption.click();	
		return new RegisterPage(driver);
	}
	
	public void enterProductInSearchBoxField(String product) {
		searchField.sendKeys(product);
	}
	
	public SearchPage clickOnSearchBtn() {
		searchBtn.click();
		return new SearchPage(driver);
	}
	
	public LoginPage navigateToLoginPage() {
		clickOnMyAcct();
		return selectLoginOption();	
	}
	
	public RegisterPage navigateToRegisterPage() {
		clickOnMyAcct();
		return selectRegisterOption();
	}
	
	public SearchPage enterProductAndSearch(String product) {
		enterProductInSearchBoxField(product);
		return clickOnSearchBtn();
	}

}
