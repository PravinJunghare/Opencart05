package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.exception.BrowserException;
import com.qa.opencart.exception.FrameworkException;

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
		
		if (browserName == null) {
		    throw new RuntimeException("Browser value is missing in properties file");
		}

		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			// driver = new ChromeDriver()
			driver = new ChromeDriver(optionsManger.getChromeOptions());
			break;

		case "firefox":
			driver = new FirefoxDriver();
			//driver = new FirefoxDriver(optionsManger.getFirefoxOptions());
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
	// mvn clean install -Denv="Qa"
	public Properties initProp()

	{

		String envName = System.getProperty("env");
		FileInputStream ip = null;
		prop=new Properties();
		try {
			if (envName == null) {
				System.out.println("env is null ,hence running on QA env");
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");

			} else {
				System.out.println("Running test cases on environment  :" + envName);
				switch (envName.toLowerCase().trim()) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
					break;

				case "uat":
					ip = new FileInputStream("./src/test/resources/config/uat.config.properties");
					break;

				default:
					throw new FrameworkException("==INVALID ENVIRONMENT NAME== :"+envName);
				}

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();

		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}

}