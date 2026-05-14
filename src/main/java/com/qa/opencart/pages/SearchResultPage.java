package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.qa.opencart.constant.AppConstant.*;//
import com.qa.opencart.utils.ElementUtil;

public class SearchResultPage {
	private WebDriver driver;

	private ElementUtil eleUtil;
	private By searchproductResult = By.cssSelector("div#content div.product-layout");

	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public int getSearchProductCount() {
		int productCount = eleUtil.waitForElementsVisible(searchproductResult, DEFAULT_MEDIUM_TIMEOUT).size();
		System.out.println("Search Product Count:" + productCount);
		return productCount;
	}

	public ProductInfoPage selectProduct(String productName) {
		By prodLocator = By.linkText(productName);// dynamic locator
		eleUtil.waitForElementVisible(prodLocator, DEFAULT_MEDIUM_TIMEOUT).click();
		return new ProductInfoPage(driver);
	}

}