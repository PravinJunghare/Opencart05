package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import static com.qa.opencart.constant.AppConstant.*;// Static Import:To remove Writing AppConstant. each time

public class LoginPageTest extends BaseTest {

	@Feature("F01: Opencart- login feature ")
	@Epic("Epic 01:Design pages for opencart application")
	@Story("US 01:Design LoginPage for Opencart application")

	@Severity(SeverityLevel.MINOR)
	@Description("Checking Login Page Title....")
	@Owner("PJ")

	@Test(priority = 1)
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		ChainTestListener.log("Checking Loginpage Title");
		// Assert.assertEquals(actTitle, "Account Login");
		Assert.assertEquals(actTitle, LOGIN_PAGE_TITLE_VALUE);
	}
	@Severity(SeverityLevel.MINOR)
	@Description("Checking Login Page Url....")
	@Test(priority = 2)
	public void loginPageUrlTest() {
		String actUrl = loginPage.getLoginUrl();
		ChainTestListener.log("Checking Loginpage Url");
		Assert.assertTrue(actUrl.contains(LOGIN_PAGE_URL_FRACTION_VALUE));

	}
	@Severity(SeverityLevel.MINOR)
	@Description("Checking Login forgotpasslink....")
	@Test(priority = 3)
	public void forgotpassLinkExistTest() {
		ChainTestListener.log("Checking forgotPasswordUrl");
		Assert.assertTrue(loginPage.isForgotpwdLinkExits());
	}
	@Severity(SeverityLevel.BLOCKER)
	@Description("Checking user login ....")
	@Test(priority = Short.MAX_VALUE)
	public void loginTest() {
		// String actAccpageTitle = loginPage.doLogin("feb02@gmail.com", "1234");
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accountsPage.getAccPageTitle(), ACCOUNTS_PAGE_TITLE_VALUE);

	}
}