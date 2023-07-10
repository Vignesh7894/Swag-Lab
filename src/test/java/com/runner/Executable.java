package com.runner;

import com.pages.LoginPage;
import com.utility.GlobalLibraries;

public class Executable extends GlobalLibraries {
public static void main(String[] args) {
	lanuchChrome();
	passURL("https://www.saucedemo.com/");
	WaitImpcitwaitCall(5);
	LoginPage lp = new LoginPage();
	String user="standard_user";
	lp.getUserName().sendKeys(user);
	
}
}
