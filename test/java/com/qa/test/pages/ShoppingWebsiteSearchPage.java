package com.qa.test.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShoppingWebsiteSearchPage {
	
	@FindBy(id = "search_query_top")
	WebElement searchBar;
	
	public void search(String input) {
		searchBar.sendKeys(input);
		searchBar.sendKeys(Keys.ENTER);
	}

}
