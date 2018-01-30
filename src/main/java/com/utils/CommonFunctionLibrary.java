package com.utils;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.Scenario;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class CommonFunctionLibrary {

	WebDriver driver;
	WebDriverWait wait;
	public Dimension size;
	public static String month;
	public static String date;

	public CommonFunctionLibrary(WebDriver driver) {
		this.driver = driver;
	}

	public void embedScreenshot(Scenario scenario) {
		if (scenario.isFailed()) {
			try {
				final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				scenario.embed(screenshot, "image/png");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * This function fires events on device keys
	 * 
	 * @param key
	 * @param noOfClk
	 */
	public void deviceKeyAndroid(int key) {
		((AndroidDriver) driver).pressKeyCode(key);
	}

	public WebElement findElement(By locator, int timeoutSeconds) {
		wait = new WebDriverWait(driver, timeoutSeconds);
		WebElement elem = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		if (elem != null) {
			return elem;
		} else {
			return null;
		}
	}

}
