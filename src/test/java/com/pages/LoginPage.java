package com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.utility.GlobalLibraries;

public class LoginPage extends GlobalLibraries{
	
	public LoginPage() {
		PageFactory.initElements(driver, this);		
	}
	
	@FindBy(id="user-name")
	private WebElement userName;
	
	@FindBy(id="password")
	private WebElement password;
	
	@FindBy(xpath="//input[@id='login-button']")
	private WebElement submit;
	
	@FindBy(xpath="//span[text()='Products']")
	private WebElement productCheck;

	public WebElement getUserName() {
		return userName;
	}

	public WebElement getPassword() {
		return password;
	}

	public WebElement getSubmit() {
		return submit;
	}
	
	public WebElement getProdTitle() {
		return productCheck;
	}

}
