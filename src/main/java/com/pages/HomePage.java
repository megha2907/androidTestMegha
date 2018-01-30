package com.pages;

import java.util.Calendar;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.apache.log4j.Logger;
import com.demo.common.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;

public class HomePage extends BasePage {

	public static Object obj;
	private static Logger logger = Logger.getLogger(HomePage.class);


	public HomePage(WebDriver driver) {
		super(driver);
		logger.info("Home page is called");

	}

	public void validateSplashScreen() {
		boolean flag=true;
		long starttime=Calendar.getInstance().getTimeInMillis();
		long endtime = 0;
		while(flag)
		{
			if(!findElement(MobileBy.AndroidUIAutomator("new UiSelector().className(android.widget.TextView)"), 10).getText().equalsIgnoreCase("Splash Screen"))
			{
			flag=false;	
			endtime=Calendar.getInstance().getTimeInMillis();
			}
		}
		
		System.out.println("time"+(endtime-starttime));
		logger.info("Spalsh screen time in second is:"+((endtime-starttime)/1000));
	
	}

	public void clickOnData() {
		buttonClick(MobileBy.AndroidUIAutomator("new UiSelector().resourceIdMatches(\".*/cardItem\").index(0)"), 10);

	}

	public void validateScreenTitle(String title) throws InterruptedException {
		String l = "";
		if (isElementPresent(
				MobileBy.AndroidUIAutomator("new UiSelector().resourceIdMatches(\".*/screen_title_textView\")"), 10)) {
			l = findElement(
					MobileBy.AndroidUIAutomator("new UiSelector().resourceIdMatches(\".*/screen_title_textView\")"), 10)
							.getText();

			if (l.equalsIgnoreCase(title.substring(1, title.length() - 1))) {
				logger.info("User is navigated to " + title + " screen");
			} else {
				logger.error("User is not navigated to details screen");

				logger.info("Something went wrong");
				Assert.fail();
			}

		} else if (isElementPresent(
				MobileBy.AndroidUIAutomator("new UiSelector().resourceIdMatches(\".*/alertTitle\")"), 10)) {
			String d = findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceIdMatches(\".*/message\")"),
					10).getText();
			logger.warn(d);
			Assert.fail();
		}

		Thread.sleep(10000);
	}

	public void naviagateToBackScreen() {
		driver.navigate().back();
		if (isElementPresent(
				MobileBy.AndroidUIAutomator("new UiSelector().resourceIdMatches(\".*/screen_title_textView\")"), 10)) {
			if (findElement(
					MobileBy.AndroidUIAutomator("new UiSelector().resourceIdMatches(\".*/screen_title_textView\")"), 10)
							.getText().equalsIgnoreCase("List")) {
				logger.info("User is navigated to back screen");

			} else {
				logger.error("User is not navigated to back screen");
				Assert.fail();

			}

		} else {
			logger.warn("Element is not present");
			Assert.fail();

		}
	}

	public void clickOnLastItem() throws InterruptedException {
		boolean flag = true;
		String old = "";
		while (flag) {

			String newstr = findElement(
					MobileBy.AndroidUIAutomator(
							"new UiSelector().className(android.widget.LinearLayout).index(4).childSelector(new UiSelector().resourceIdMatches(\".*/cardItem\")).childSelector(new UiSelector().className(android.widget.LinearLayout).index(0)).childSelector(new UiSelector().resourceIdMatches(\".*/item_title_textView\"))"),
					10).getText();

			if (!old.equals("")) {
				if (old.equalsIgnoreCase(newstr)) {
					flag = false;
					buttonClick(
							MobileBy.AndroidUIAutomator(
									"new UiSelector().className(android.widget.LinearLayout).index(4).childSelector(new UiSelector().resourceIdMatches(\".*/cardItem\"))"),
							10);
				} else {
					org.openqa.selenium.Dimension size = driver.manage().window().getSize();
					int starty = (int) (size.height * 0.90);
					int endy = (int) (size.height * 0.05);
					int startx = size.width / 2;

					// Swipe from Bottom to Top.
					((AppiumDriver) driver).swipe(startx, starty, startx, endy, 1000);
					// Thread.sleep(1000);
					old = newstr;
				}
			} else {
				old = newstr;
				org.openqa.selenium.Dimension size = driver.manage().window().getSize();
				int starty = (int) (size.height * 0.90);
				int endy = (int) (size.height * 0.05);
				int startx = size.width / 2;

				((AppiumDriver) driver).swipe(startx, starty, startx, endy, 1000);
		
			}

		}
	

	}

	public void validateDetailImageData() {
		if (isElementPresent(
				MobileBy.AndroidUIAutomator(
						"new UiSelector().className(android.widget.LinearLayout).index(1).childSelector(new UiSelector().resourceIdMatches(\".*/cardItem\").className(android.widget.FrameLayout).index(0))"),
				10)
				&& isElementPresent(
						MobileBy.AndroidUIAutomator(
								"new UiSelector().className(android.widget.LinearLayout).index(3).childSelector(new UiSelector().resourceIdMatches(\".*/cardItem\").className(android.widget.FrameLayout).index(0))"),
						10)) {
			logger.info("Image and description is loaded and is present on screen");
		} else if(isElementPresent(MobileBy.AndroidUIAutomator("new UiSelector().resourceIdMatches(\".*/imageProgressBar\")"), 10)) {
			logger.warn("Image is not loaded completely");
			Assert.fail();
		}
	}

}
