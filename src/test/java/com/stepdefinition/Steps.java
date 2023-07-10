package com.stepdefinition;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.DoublePredicate;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.pages.LoginPage;
import com.utility.GlobalLibraries;

import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Steps extends GlobalLibraries {
	public static WebDriver driver;
	LoginPage lp = new LoginPage();
	double sum = 0.0;
	int checker = 0;
	double cartSum = 0.0;

	@Given("User must be in login page")
	public void user_must_be_in_login_page() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com/");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@When("User must login with valid username and password")
	public void user_must_login_with_valid_username_and_password(io.cucumber.datatable.DataTable dataTable) {
		List<String> list = dataTable.asList();
		driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys(list.get(0));
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(list.get(1));
	}

	@When("User must login with valid {string} and {string}")
	public void user_must_login_with_valid_and(String user, String password) {

		driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys(user);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
	}

	@When("User must click on login button")
	public void user_must_click_on_login_button() {
		driver.findElement(By.xpath("//input[@id='login-button']")).click();
	}

	@Then("Verify user reddirected to home page")
	public void verify_user_reddirected_to_home_page() {
		WebElement prod = driver.findElement(By.xpath("//span[text()='Products']"));
		Assert.assertEquals("Products", prod.getText());
	}

	@When("check all the products list")
	public void check_all_the_products_list() {
		List<WebElement> allProducts = driver.findElements(By.xpath("//div[@class='inventory_item_description']"));
		List<WebElement> allTitle = driver.findElements(By.xpath("//div[@class='inventory_item_name']"));
		for (int i = 0; i < allTitle.size(); i++) {
			String text = allTitle.get(i).getText();
			System.out.println(text);
		}
	}

	@Then("check total products are matching with expected")
	public void check_total_products_are_matching_with_expected() {
		List<WebElement> allProducts = driver.findElements(By.xpath("//div[@class='inventory_item_description']"));
		Assert.assertEquals(6, allProducts.size());
	}

	@When("user select product to cart")
	public void user_select_to_cart(io.cucumber.datatable.DataTable data) {
		List<String> datalist = data.asList();
		List<WebElement> allTitle = driver.findElements(By.xpath("//div[@class='inventory_item_name']"));

		for (WebElement element : allTitle) {
			for (int i = 0; i < datalist.size(); i++) {
				String prod = datalist.get(i);
				if (element.getText().equals(prod)) {
					System.out.println(prod);
					// driver.findElement(By.xpath("//button[text()='Add to cart']")).click();
					driver.findElement(By.xpath("//div[text()='" + prod
							+ "']//ancestor::div[@class='inventory_item_label']//following-sibling::div[@class='pricebar']//child::button"))
							.click();
					checker++;
					break;
				}
			}
		}

	}

	@When("user open cart page")
	public void user_open_cart_page() {
		driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
	}

	@Then("verify total no of products in cart")
	public void verify_total_no_of_products() {
		int size = driver.findElements(By.xpath("//div[@class='cart_item_label']")).size();
		Assert.assertTrue(checker == size);
		driver.close();
	}

	@Then("verify sum of price in cart")
	public void verify_sum_of_price_in_cart() {
		List<WebElement> prices = driver.findElements(By.xpath("//div[@class='inventory_item_price']"));
		for (WebElement price : prices) {
			double cartf = Double.parseDouble(price.getText().replace("$", ""));
			cartSum += cartf;
		}
		Assert.assertTrue(cartSum == sum);
		driver.close();
	}

	@When("user checkout the cart")
	public void user_checkout_the_cart() {
		driver.findElement(By.xpath("//button[@id='checkout']")).click();
	}

	@When("user enter {string},{string},{string}")
	public void user_enter(String string, String string2, String string3) {
		driver.findElement(By.xpath("//input[@id='first-name']")).sendKeys(string);
		driver.findElement(By.xpath("//input[@id='last-name']")).sendKeys(string2);
		driver.findElement(By.xpath("//input[@id='postal-code']")).sendKeys(string3);
	}

	@When("user click on continue")
	public void user_click_on_continue() {
		driver.findElement(By.xpath("//input[@id='continue']")).click();
	}

	@Then("verify sum of price in checkout")
	public void verify_sum_of_price_in_checkout() {
		String[] itemText = driver.findElement(By.xpath("//div[@class='summary_subtotal_label']")).getText()
				.split("[$]");
		String[] taxText = driver.findElement(By.xpath("//div[@class='summary_tax_label']")).getText().split("[$]");

		double tot = Double.parseDouble(itemText[1]) + Double.parseDouble(taxText[1]);

		String sumText = driver.findElement(By.xpath("//div[@class='summary_info_label summary_total_label']"))
				.getText();
		System.out.println(sumText);
		String[] split = sumText.split("[$]");
		Assert.assertTrue(tot == Double.parseDouble(split[1]));

	}

	@Then("user click on fnish")
	public void user_click_on_fnish() {
		driver.findElement(By.xpath("//button[@id='finish']")).click();
	}

	@Then("verify for order confirmation")
	public void verify_for_order_confirmation() {
		WebElement order = driver.findElement(By.xpath("//h2[text()='Thank you for your order!']"));
		Assert.assertTrue(order.isDisplayed());
	}

}
