package com.qa.test;

//import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.qa.test.pages.ShoppingWebsiteResultPage;
import com.qa.test.pages.ShoppingWebsiteSearchPage;

public class ShoppingWebsite {
	
	private WebDriver driver;
	private ExtentTest test;
	private static ExtentReports report;
	
	@BeforeClass
	public static void setup() {
		report = new ExtentReports();
		ExtentHtmlReporter html = new ExtentHtmlReporter("test-output/extent-report.html");
		html.config().setAutoCreateRelativePathMedia(true);
		report.attachReporter(html);
	}
	
	@Before
	public void init() {
		ChromeOptions opts = new ChromeOptions();
		opts.setHeadless(false);
		driver = new ChromeDriver(opts);
		driver.manage().window().maximize();
	}

//	@Test
//	public void test() {
//		driver.get("http://automationpractice.com/index.php");
//		
//		WebElement searchBar = driver.findElement(By.id("search_query_top"));
//		searchBar.sendKeys("Dress");
//		searchBar.sendKeys(Keys.ENTER);
//		
//		WebElement searchResults = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div[2]/ul/li[1]/div/div[2]/h5/a"));
//		System.out.println(searchResults.getText());
//		
//		assertEquals("Printed Summer Dress", searchResults.getText());
//	}
	
	@Test
	public void testPOM() throws InterruptedException {
		driver.manage().window().maximize();
		driver.get("http://automationpractice.com/index.php");
		this.test = report.createTest("testPOM");
		
		final String input = "Dress";
		final String result = "Printed Summer Dress";
		
		ShoppingWebsiteSearchPage searchPage = PageFactory.initElements(driver, ShoppingWebsiteSearchPage.class);
		searchPage.search(input);
		
		ShoppingWebsiteResultPage resultPage = PageFactory.initElements(driver, ShoppingWebsiteResultPage.class);
		
		if(resultPage.results().equals(result)){
			test.pass("Item was found on results page");
		}else {
			test.fail("Item not found on results page");
			fail();
		}
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}

	@AfterClass
	public static void tearDownClass() {
		report.flush();
	}
	
}
