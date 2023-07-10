package com.utility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GlobalLibraries {
	public static WebDriver driver;
	public static WebDriver lanuchChrome() {
		WebDriverManager.chromedriver().setup();
		return driver = new ChromeDriver();
	}

	// 2.lunchFirefox
	public static void lanuchFirefox() {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
	}

	// 3.lunchEdge
	public static void lanuchEdge() {
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();

	}
	
	// 4.get()
		public static void passURL(String url) {
			driver.get(url);
			maxWin();
		}

		// 5.Maximize
		public static void maxWin() {
			driver.manage().window().maximize();
		}
        
		// 6. ImplicitWait
		public static void WaitImpcitwaitCall(int sec) {
			driver.manage().timeouts().implicitlyWait(sec,TimeUnit.SECONDS);
		}
		
		//7 . SendKeys
		public static void sendKeyCall(WebElement ele, String passValue) {
			ele.sendKeys(passValue);
		}
		
		// 8. click
		public static void clickbtn(WebElement ele) {
			ele.click();
		}
		
}
