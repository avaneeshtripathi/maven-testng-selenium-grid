package com.seleniumgrid.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.seleniumgrid.base.BaseTest;

public class FirstTest extends BaseTest {
	@Test
	public void logInTest() {
		// Open the page
		String url = "https://www.google.com/";
		driver.get(url);
		logInfo("Page is opened.");

		// Search a keyword
		WebElement inputField = driver.findElement(By.cssSelector("input[type=text]"));
		inputField.sendKeys("Lucknow");

		// Push search button
		WebElement searchButton = driver.findElements(By.cssSelector("input[type=submit]")).get(2);
		searchButton.click();
		logInfo("Clicked Search button.");

		// Verifications:

		// 1 - New URL
		String newUrl = driver.getCurrentUrl();

		Assert.assertNotEquals(url, newUrl);
		logInfo("URL verification is complete.");

		// 2 - Search results are visible
		WebElement resultStats = driver.findElement(By.id("result-stats"));
		Assert.assertTrue(resultStats.isDisplayed(), "Search results are not visible.");
		logInfo("Search results are visible.");
		logInfo(resultStats.getText());
	}
}