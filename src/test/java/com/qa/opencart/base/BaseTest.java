package com.qa.opencart.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.opencart.factory.Driverfactory;
import com.qa.opencart.pages.LoginPage;

public class BaseTest {

	Driverfactory df;
	WebDriver driver;

	protected LoginPage loginPage;

	@BeforeTest
	public void setUp() {
		df = new Driverfactory();// created driver factory object
		driver = df.initDriver("chrome");// to call initDriver method to get driver
		loginPage = new LoginPage(driver);
	}

	@AfterTest
	public void teardown() {
		driver.quit();
	}

}