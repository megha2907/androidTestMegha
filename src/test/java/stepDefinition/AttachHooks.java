package stepDefinition;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.utils.CommonFunctionLibrary;
import com.utils.ConfigManager;
import com.utils.DriverFactory;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class AttachHooks {

	private Scenario scenario;
	public static WebDriver driver;
	DesiredCapabilities capabilities;
	private static Logger LOGGER = LoggerFactory.getLogger(AttachHooks.class);
	public static boolean flag = false, saleFlag = false, unsoldFlag = false, winFlag = false;
	CommonFunctionLibrary functionLibrary;

	@Before
	public void setUp(Scenario scenario) throws IOException {
		LOGGER.info("Inside set up method of before hook");
		ConfigManager.loadConfig();
		this.scenario = scenario;
		System.out.println(scenario.getName());
		/**
		 * This method Is responsible for executing test cases on Native Mobile
		 * apps.
		 */
		if (ConfigManager.getProperty("ExecutionPlatform").equalsIgnoreCase("Mobile")) {
			if (ConfigManager.getProperty("MachineName").equalsIgnoreCase("Windows")) {
				DriverFactory.appiumStartWindows();
			} else if (ConfigManager.getProperty("MachineName").equalsIgnoreCase("Mac")) {
				DriverFactory.appiumStartMac();
			}
			if (ConfigManager.getProperty("PlatformName").equalsIgnoreCase("Android")) {
				capabilities = new DesiredCapabilities();
				capabilities.setCapability("emulator", true);
				capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, ConfigManager.getProperty("DeviceName"));
				capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,
						ConfigManager.getProperty("PlatformName"));
				capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,
						ConfigManager.getProperty("PlatformVersion"));
				capabilities.setCapability(MobileCapabilityType.APP, ConfigManager.getProperty("ApkPath"));
		
				capabilities.setCapability("autoAcceptAlerts", true);
				capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "60000");

				try {
					driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	
			}
			}
		}

		

	@After
	public void tearDown() throws InstantiationException, IllegalAccessException, IOException {
//		if (ConfigManager.getProperty("ExecutionPlatform").equalsIgnoreCase("Mobile")) {
//			functionLibrary.embedScreenshot(scenario);
//			driver.quit();
//		} 

	}

}