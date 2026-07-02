package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.base.BaseTest;
import static com.qa.opencart.constant.AppConstant.*;// Static Import:To remove Writing AppConstant. each time

public class LoginPageTest extends BaseTest {

	@Test(priority = 1)
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		ChainTestListener.log("Checking Loginpage Title");
		// Assert.assertEquals(actTitle, "Account Login");
		Assert.assertEquals(actTitle, LOGIN_PAGE_TITLE_VALUE);
	}

	@Test(priority = 2)
	public void loginPageUrlTest() {
		String actUrl = loginPage.getLoginUrl();
		ChainTestListener.log("Checking Loginpage Url");
		Assert.assertTrue(actUrl.contains(LOGIN_PAGE_URL_FRACTION_VALUE));

	}

	@Test(priority = 3)
	public void forgotpassLinkExistTest() {
		ChainTestListener.log("Checking forgotPasswordUrl");
		Assert.assertTrue(loginPage.isForgotpwdLinkExits());
	}

	@Test(priority = Short.MAX_VALUE)
	public void loginTest() {
		// String actAccpageTitle = loginPage.doLogin("feb02@gmail.com", "1234");
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accountsPage.getAccPageTitle(), ACCOUNTS_PAGE_TITLE_VALUE);

	}
}