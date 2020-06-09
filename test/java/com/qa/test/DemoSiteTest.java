package com.qa.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.qa.test.pages.DemoSiteLoginPage;
import com.qa.test.pages.DemoSiteRegisterPage;

public class DemoSiteTest {
	
	private WebDriver driver;
	
	@Before
	public void init() {
		driver = new ChromeDriver();
	}
	
	@Test
	public void test() {
		driver.manage().window().maximize();
		driver.get("http://www.thedemosite.co.uk/addauser.php");
		
		final String username = "user";
		final String password = "password";
		
		WebElement usernameInput = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[1]/td[2]/p/input"));
		WebElement passwordInput = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[2]/td[2]/p/input"));
		usernameInput.sendKeys(username);
		passwordInput.sendKeys(password);
		
		WebElement saveButton = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[3]/td[2]/p/input"));
		saveButton.click();
		
		WebElement loginButton = driver.findElement(By.xpath("/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[4]"));
		loginButton.click();
		
		WebElement usernameLogin = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[1]/td[2]/p/input"));
		WebElement passwordLogin = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/p/input"));
		usernameLogin.sendKeys(username);
		passwordLogin.sendKeys(password);
		
		WebElement testLoginButton = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[3]/td[2]/p/input"));
		testLoginButton.click();
		
		WebElement loginStatus = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/big/blockquote/blockquote/font/center/b"));
		loginStatus.getText();
		
		assertEquals("**Successful Login**", loginStatus.getText());
	}
	
	@Test
	public void testPOM() {
		driver.manage().window().maximize();
		driver.get("http://www.thedemosite.co.uk/addauser.php");
		
		final String username = "user";
		final String password = "password";
		
		DemoSiteRegisterPage registerPage = PageFactory.initElements(driver, DemoSiteRegisterPage.class);
		
		registerPage.input(username,password);
		registerPage.save();
		
		driver.manage().window().maximize();
		driver.get("http://www.thedemosite.co.uk/login.php");
		
		DemoSiteLoginPage loginPage = PageFactory.initElements(driver, DemoSiteLoginPage.class);
		
		loginPage.input(username, password);
		loginPage.login();
		loginPage.status();
		
		assertTrue(loginPage.status().contains("**Successful Login**"));
		
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}
	
}
