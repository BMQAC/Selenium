package com.qa.test.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShoppingWebsiteResultPage {
	
	@FindBy(xpath = "/html/body/div/div[2]/div/div[3]/div[2]/ul/li[1]/div/div[2]/h5/a")
	WebElement searchResult;
	
	public String results() {
		return searchResult.getText();
	}
}
