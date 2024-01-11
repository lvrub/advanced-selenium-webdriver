package runner;

import com.herokuapp.theinternet.base.BrowserDriverFactory;
import com.herokuapp.theinternet.base.Screenshot;
import io.cucumber.java.Scenario;
import io.cucumber.testng.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.xml.XmlTest;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

@CucumberOptions(tags = "",
        features = {"src/test/resources/features"},
        glue = {"definitions"},
        plugin = {"pretty", "html:target/cucumber-report.html", "json:target/cucumber.json"},
        publish = true)

public class TestRunner extends AbstractTestNGCucumberTests {
    private final static ThreadLocal<WebDriver> threadLocalWebDriver = new ThreadLocal<>();
    private final static ThreadLocal<Logger> loggerThreadLocal = new ThreadLocal<>();
    private final static ThreadLocal<BrowserDriverFactory> factoryThreadLocal = new ThreadLocal<>();
    private TestNGCucumberRunner testNGCucumberRunner;
    private static WebDriver driver;
    private static Logger log;
    private static String profile;
    private static String deviceName;
//    private static BrowserDriverFactory factory;


    public static synchronized Logger getLogger() {
//        if (loggerThreadLocal.get() == null) {
//            loggerThreadLocal.set(log);
//        }
        return loggerThreadLocal.get();
    }

    public static synchronized WebDriver getWebDriver() {
        if (threadLocalWebDriver.get() == null) {
            driver = initializeDriver(factoryThreadLocal.get(), profile, deviceName);
            driver.manage().window().maximize();
            threadLocalWebDriver.set(driver);
        }
        return threadLocalWebDriver.get();
    }


    @Parameters({"browser", "chromeProfile", "deviceName"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(Method method, @Optional("chrome") String browser, @Optional String profile,
                      @Optional String deviceName, ITestContext ctx) {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
        String testName = ctx.getCurrentXmlTest().getName();
        log = LogManager.getLogger(testName);
        setLoggerThreadLocal(log);
        getLogger().info("Browser from config: " + browser);

        BrowserDriverFactory factory = new BrowserDriverFactory(browser, getLogger());
//        this.factory = factory;
        this.profile = profile;
        this.deviceName = deviceName;
        factoryThreadLocal.set(factory);
    }

    private synchronized void setLoggerThreadLocal(Logger log) {
        loggerThreadLocal.set(log);
    }

    private static WebDriver initializeDriver(BrowserDriverFactory factory, String profile, String deviceName) {
        if (profile != null) {
            return factoryThreadLocal.get().createChromeWithProfile(profile);
        } else if (deviceName != null) {
            return factoryThreadLocal.get().createChromeWithMobileEmulation(deviceName);
        } else {
            return factoryThreadLocal.get().createDriver();
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestContext ctx, ITestResult result) throws Exception {
            if (result.getStatus() == ITestResult.FAILURE) {
                new Screenshot(getWebDriver()).takeScreenshot(result, ctx);
            }
        getLogger().info("Close driver" + getWebDriver());
        getWebDriver().quit();
        threadLocalWebDriver.remove();
        loggerThreadLocal.remove();
        factoryThreadLocal.remove();
    }

    @Override
    @Test(dataProvider = "scenarios")
    public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {
        log.info(featureWrapper);
        super.runScenario(pickleWrapper, featureWrapper);
    }

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

}

