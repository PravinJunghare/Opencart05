package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.exception.BrowserException;

public class Driverfactory {

	public WebDriver driver;
	public Properties prop;
	OptionsManager optionsManger;
	public static String isHighlight;

	/**
	 * this method is initializing the driver on the basis of browser name
	 * 
	 * @param browserName
	 * @return this returns the driver
	 */
	// public WebDriver initDriver(String browserName)
	public WebDriver initDriver(Properties prop) {
		String browserName = prop.getProperty("browser");
		optionsManger = new OptionsManager(prop);
		isHighlight = prop.getProperty("highlight");
		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			// driver = new ChromeDriver()
			driver = new ChromeDriver(optionsManger.getChromeOptions());
			break;

		case "firefox":
			// driver = new FirefoxDriver();
			driver = new FirefoxDriver(optionsManger.getFirefoxOptions());
			break;

		case "edge":
			// driver = new EdgeDriver();
			driver = new EdgeDriver(optionsManger.getEdgeOptions());
			break;

		case "safari":
			driver = new SafariDriver();
			break;

		default:
			System.out.println("Please enter the correct browserName" + browserName);
			throw new BrowserException("===Invalid Browser=====" + browserName);
		}

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		// driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
		driver.get(prop.getProperty("url"));

		return driver;

	}

	/**
	 * This method is reading properties from properties file
	 * 
	 * @return
	 */

	public Properties initProp() {
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
			prop.load(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}

}