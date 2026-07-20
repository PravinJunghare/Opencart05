
package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

import static com.qa.opencart.constant.AppConstant.*;// Static Import:To remove Writing AppConstant. each time

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	// 1. Private By locators
	private final By username = By.id("input-email");
	private final By password = By.id("input-password");
	private final By loginBtn = By.xpath("//input[@value='Login']");
	private final By forgottenPwdlink = By.id("input-email");
	private final By registerlink = By.linkText("Register");

	// 2. Page Constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// 3.Page Actions/Methods

	// 3.Page Actions/Methods
	@Step("Getting LoginPage Title")
	public String getLoginPageTitle() {
		// String title = driver.getTitle();
		String title = eleUtil.waitForTitleContainsAndFetch(DEFAULT_SHORT_TIMEOUT, LOGIN_PAGE_TITLE_VALUE);
		return title;
	}
	@Step("Getting LoginPage Url")
	public String getLoginUrl() {
		// String url = driver.getCurrentUrl();
		String url = eleUtil.waitForURLContainsAndFetch(DEFAULT_MEDIUM_TIMEOUT, LOGIN_PAGE_URL_FRACTION_VALUE);
		return url;
	}
	@Step("Getting LoginPage Forgotpasslink")
	public boolean isForgotpwdLinkExits() {

		// return driver.findElement(forgottenPwdlink).isDisplayed();
		return eleUtil.doElementIsDisplayed(forgottenPwdlink);

	}
	@Step("Getting LoginPage regitserurl")
	public boolean isregistertLinkExits() {
		// return driver.findElement(registerlink).isDisplayed();
		return eleUtil.doElementIsDisplayed(registerlink);

	}
	@Step("Login with valid username: {0} and password: {1}")
	public AccountsPage doLogin(String un, String pwd) {
		// driver.findElement(username).sendKeys(un);
		// driver.findElement(password).sendKeys(pwd);
		// driver.findElement(loginBtn).click();

		eleUtil.waitForElementVisible(username, DEFAULT_MEDIUM_TIMEOUT).sendKeys(un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);

	}
	@Step("Navigating to register Page ")
	public RegistrationPage navigateToRegistrationPage() {
		{
			//eleUtil.doClick(registerlink);
			eleUtil.clickWhenReady(DEFAULT_MEDIUM_TIMEOUT, registerlink);
			return new RegistrationPage(driver);
		}
	}

}