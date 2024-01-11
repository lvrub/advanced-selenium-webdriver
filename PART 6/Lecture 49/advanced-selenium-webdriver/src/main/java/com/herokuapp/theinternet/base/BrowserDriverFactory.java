package com.herokuapp.theinternet.base;

import java.util.HashMap;
import java.util.Map;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

public class BrowserDriverFactory {

	private WebDriver driver;
	private String browser;
	private Logger log;

	public BrowserDriverFactory(String browser, Logger log) {
		this.browser = browser.toLowerCase();
		this.log = log;
	}

	public WebDriver createDriver() {
		// Create driver
		log.info("Create driver: " + browser);

		switch (browser) {
		case "chrome":
//			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;

		case "firefox":
//			System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;

			case "edge":
//			System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
				break;
//
		case "chromeheadless":
//			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--headless");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(chromeOptions);
			break;
//
		case "firefoxheadless":
//			System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
			FirefoxBinary firefoxBinary = new FirefoxBinary();
			firefoxBinary.addCommandLineOptions("--headless");
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.setBinary(firefoxBinary);
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver(firefoxOptions);
			break;
//
//		case "phantomjs":
//			System.setProperty("phantomjs.binary.path", "src/main/resources/phantomjs.exe");
//			driver = new PhantomJSDriver();
//			break;
//
//
//		case "htmlunit":
//			driver = new HtmlUnitDriver();
//			break;

		default:
			System.out.println("Do not know how to start: " + browser + ", starting chrome.");
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		}

		return driver;
	}

	public WebDriver createChromeWithProfile(String profile) {
		log.info("Starting chrome driver with profile: " + profile);
		ChromeOptions chromeOptions = new ChromeOptions();
//		chromeOptions.addArguments("user-data-dir=src/main/resources/Profiles/" + profile);
		chromeOptions.addArguments("start-maximized"); // open Browser in maximized mode
		chromeOptions.addArguments("disable-infobars"); // disabling infobars
		chromeOptions.addArguments("--disable-extensions"); // disabling extensions
		chromeOptions.addArguments("--disable-gpu"); // applicable to windows os only
		chromeOptions.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
		chromeOptions.addArguments("--no-sandbox"); // Bypass OS security model
		chromeOptions.addArguments("log-level=0");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(chromeOptions);
		return driver;
	}

	public WebDriver createChromeWithMobileEmulation(String deviceName) {
		log.info("Starting driver with " + deviceName + " emulation]");
		Map<String, String> mobileEmulation = new HashMap<>();
		mobileEmulation.put("deviceName", deviceName);
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
//		chromeOptions.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

//		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(chromeOptions);
		return driver;
	}
}
