package com.demo.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.skilrock.utils.CommonFunctionLibrary;

public class BasePage {

	protected static WebDriver driver;
	static WebDriverWait wait;
	public static CommonFunctionLibrary functionLibrary;
	private static Logger LOGGER = LoggerFactory.getLogger(BasePage.class);
	public static ArrayList<HashMap<String, String>> trackTktUiListData;
	public static HashMap<String, String> trackTktUiData;
	public static HashMap<String, String> myTktUiData;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		functionLibrary = new CommonFunctionLibrary(this.driver);

	}

	public boolean isElementPresent(By locator, int timeoutInSeconds) {
		try {
			wait = new WebDriverWait(driver, timeoutInSeconds);
			WebElement elem = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			if (elem != null) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			return false;
		}

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

	public List<String> findElements(By locator, int timeoutSeconds) {
		wait = new WebDriverWait(driver, timeoutSeconds);
		List<WebElement> elem = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		List<String> temp = new ArrayList<String>();
		if (elem != null) {
			for (WebElement val : elem) {
				temp.add(val.getText());
			}
			return temp;
		} else {
			return null;
		}
	}

	/**
	 * To click an element
	 */
	public boolean buttonClick(By locator, int timeoutSeconds) {
		try {
			WebElement element = findElement(locator, timeoutSeconds);
			element.click();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}