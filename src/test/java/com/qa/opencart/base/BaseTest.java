package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
//import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.aventstack.chaintest.plugins.ChainTestListener;
//import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.factory.Driverfactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegistrationPage;
//import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultPage;

//@Listeners(ChainTestListener.class)
public class BaseTest {

	Driverfactory df;
	WebDriver driver;
	protected Properties prop;

	

	protected LoginPage loginPage;
	protected AccountsPage accountsPage;
	protected SearchResultPage searchPage;
	protected ProductInfoPage productInfoPage;
	protected SoftAssert softAssert;
   protected RegistrationPage registrationPage;

   @Parameters({"browser"})
	@BeforeTest
	public void setUp(String browserName) {
		df = new Driverfactory();// created driver factory object
		prop = df.initProp();// df.initProp() will give prop reference
		
		//broweserName is passed from .xml
		if(browserName!=null)
		{
			prop.setProperty("browser", browserName);
		}
		// driver = df.initDriver("chrome");// to call initDriver method to get driver
		driver = df.initDriver(prop);
		loginPage = new LoginPage(driver);
		softAssert = new SoftAssert();
	}
   
   

	@AfterMethod // will be running after each @test method
	public void attachScreenshot(ITestResult result) {
		// ******For failed Test Cases Screenshot************
		
		if (!result.isSuccess()) {// only for failure test cases -- true
			// log.info("---screenshot is taken---");
			ChainTestListener.embed(Driverfactory.getScreenshotFile(), "image/png");
		}
		// For All Test Cases
		// ChainTestListener.embed(Driverfactory.getScreenshotFile(), "image/png");

	}

	@AfterTest
	public void teardown() {
		driver.quit();
	}

}