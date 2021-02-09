package com.qa.springcats.selenium;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;


public class GooglePageTest {
	private static RemoteWebDriver driver;
	private static WebElement targ;
	private final String url = "http://www.google.com";

	@BeforeAll
	public static void beforeAll() {
		//system property
		System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chrome/chromedriver.exe");
		driver = new ChromeDriver(chromeCfg());
	}
	@AfterAll
		public static void afterAll() {
		//closes the chrome driver
		driver.quit();
	}
	@Test
	public void test1() throws InterruptedException {
		driver.get(url);
		assertEquals("Google", driver.getTitle());
		Thread.sleep(1000);
	}
	@Test
	public void kittens() throws InterruptedException {
		//given that I can access google
		driver.get(url);
		//when I search kittens using the search bar
		targ = driver.findElement(By.name("q"));
		targ.sendKeys("Kittens");
		targ.submit();
		//then I should get results of kittens
		String result = driver.getTitle();
		//assertions
		assertEquals("Kittens - Google Search", result);
		Thread.sleep(5000); //stops app for specific time
	}
	@Test
	public void puppy() throws InterruptedException {
		//given that I can access google
		driver.get(url);
		//when I search puppy using the search bar
		targ = driver.findElement(By.name("q"));
		targ.sendKeys("Puppy");
		targ.submit();
		//then I should get results of puppy
		String result = driver.getTitle();
		//assertions
		assertEquals("Puppy - Google Search", result);
		Thread.sleep(5000);
	}
	@Test
	public void puppyImages() throws InterruptedException {
		//given that I can access google
		driver.get(url);
		//when I search puppy using the search bar
		targ = driver.findElement(By.name("q"));
		targ.sendKeys("Puppy");
		targ.submit();
		//and I select to view images
		targ = driver.findElementById("hdtb-msb");
		targ.click();
		//then I should get results of puppy images
		String result = driver.getTitle();
		//assertions
		assertEquals("Puppy - Google Search", result);
		Thread.sleep(5000);
	}
	public static ChromeOptions chromeCfg() {
		 Map<String, Object> prefs = new HashMap<String, Object>();
		 ChromeOptions cOptions = new ChromeOptions();
		  
		 // Settings
		 prefs.put("profile.default_content_setting_values.cookies", 2);
		 prefs.put("network.cookie.cookieBehavior", 2);
		 prefs.put("profile.block_third_party_cookies", true);

		 // Create ChromeOptions to disable Cookies pop-up
		 cOptions.setExperimentalOption("prefs", prefs);

		 return cOptions;
		 }
}
