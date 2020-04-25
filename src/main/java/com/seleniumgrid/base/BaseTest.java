package com.seleniumgrid.base;

import java.sql.Timestamp;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {
	protected WebDriver driver;
	protected String testName;

	protected void logInfo(String message) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		System.out.println(timestamp + "\tINFO " + "[" + testName + "] " + message);
	}
	
	@BeforeMethod(alwaysRun = true)
	@Parameters({ "browser" })
	protected void setUp(@Optional("firefox") String browser, ITestContext ctx) {
		// Create Driver
		DriverFactory factory = new DriverFactory(browser);

		driver = factory.createDriverGrid();
		
		// maximize browser window
		driver.manage().window().maximize();

		// Set up test name and Logger
		testName = ctx.getCurrentXmlTest().getName();
	}

	@AfterMethod(alwaysRun = true)
	protected void tearDown() {
		// Closing driver
		logInfo("Closing driver");
		driver.quit();
	}

}
