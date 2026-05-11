package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import static com.qa.opencart.constant.AppConstant.*;// Static Import:To remove Writing AppConstant. each time

public class LoginPageTest extends BaseTest {

	@Test(priority = 1)
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		// Assert.assertEquals(actTitle, "Account Login");
		Assert.assertEquals(actTitle, LOGIN_PAGE_TITLE_VALUE);
	}

	@Test(priority = 2)
	public void loginPageUrlTest() {
		String actUrl = loginPage.getLoginUrl();
		Assert.assertTrue(actUrl.contains(LOGIN_PAGE_URL_FRACTION_VALUE));

	}

	@Test(priority = 3)
	public void forgotpassLinkExistTest() {
		Assert.assertTrue(loginPage.isForgotpwdLinkExits());
	}

	@Test(priority = Short.MAX_VALUE)
	public void loginTest() {
		// String actAccpageTitle = loginPage.doLogin("feb02@gmail.com", "1234");
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accountsPage.getAccPageTitle(), ACCOUNTS_PAGE_TITLE_VALUE);

	}
}