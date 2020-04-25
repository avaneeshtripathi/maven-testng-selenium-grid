package com.seleniumgrid.base;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverFactory {
	private ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	private String browser;

	public DriverFactory(String browser) {
		this.browser = browser.toLowerCase();
	}

	public WebDriver createDriverGrid() {
		String hubUrl = "http://localhost:4444/wd/hub";
		System.out.println("Starting " + browser + " on grid");

		try {
			// Creating driver
			switch (browser) {
			case "chrome":
				ChromeOptions chromeOptions = new ChromeOptions();
				driver.set(new RemoteWebDriver(new URL(hubUrl), chromeOptions));
				break;

			case "firefox":
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				driver.set(new RemoteWebDriver(new URL(hubUrl), firefoxOptions));
				break;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return driver.get();
	}
}