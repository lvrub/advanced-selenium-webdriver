package runner;

import com.herokuapp.theinternet.base.BrowserDriverFactory;
import com.herokuapp.theinternet.base.Screenshot;
import com.herokuapp.theinternet.pages.WelcomePage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class RunnerTest {
//    static Logger log;
//    public WebDriver driver;
//
//    public static Logger getLog() {
//        return log;
//    }
//    public static WebDriver getDriver() {
//        return driver;
//    }
//    @CucumberOptions(tags = "",
//            features = {"src/test/resources/features/Login.scenarios"},
//            glue = {"definitions"},
//            plugin = {"pretty", "html:target/cucumber-report.html", "json:target/cucumber.json"},
//            publish = true)
//    public class CucumberRunnerTests extends AbstractTestNGCucumberTests {
//
//        @Parameters({ "browser", "chromeProfile", "deviceName" })
//        @BeforeMethod(alwaysRun = true)
//        public void setUp(Method method, @Optional("chrome") String browser, @Optional String profile,
//                          @Optional String deviceName, ITestContext ctx) {
//            String testName = ctx.getCurrentXmlTest().getName();
//            log = LogManager.getLogger(testName);
//
//            BrowserDriverFactory factory = new BrowserDriverFactory(browser, log);
//            if (profile != null) {
//                driver = factory.createChromeWithProfile(profile);
//            }
//            else if (deviceName != null) {
//                driver = factory.createChromeWithMobileEmulation(deviceName);
//            } else {
//                int count = 1;
//                driver = factory.createDriver();
//                System.out.println("Driver number: " + count);
//                count++;
//            }
//
//            // This sleep here is for instructor only. Students don't need this here
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            driver.manage().window().maximize();
//
//        }
//
//        @AfterMethod(alwaysRun = true)
//        public void tearDown(ITestResult result, ITestContext ctx) {
//            if (result.getStatus() == ITestResult.FAILURE) {
//                new Screenshot(driver).takeScreenshot(result, ctx);
//            }
//            log.info("Close driver");
//            // Close browser
//            driver.quit();
//        }
//    }
}
