package definitions;

import com.herokuapp.theinternet.pages.LoginPage;
import com.herokuapp.theinternet.pages.SecureAreaPage;
import com.herokuapp.theinternet.pages.WelcomePage;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import runner.TestRunner;

public class LoginDefinition1 {
    WelcomePage welcomePage;
    LoginPage loginPage;
    WebDriver driver;
    Logger log;


    private Scenario scenario;

    @Before
    public void before(Scenario scenarioVal) {
    driver = new TestRunner().getWebDriver();
    log = new TestRunner().getLogger();
        this.scenario = scenarioVal;
        log.info("Scenario: " + scenario.getName() +scenario.getId());
    }

    @Given("^user is on login page1$")
    public void user_is_on_login_page1() {
        System.out.println("user is on login page");
        welcomePage = new WelcomePage(driver, log);
        welcomePage.openPage();
    }
    @When("^user enters correct user name and password1$")
    public void user_enters_correct_user_name_and_password1() {
        System.out.println("user enters correct user name and password");
        loginPage = new LoginPage(driver, log);
        loginPage = welcomePage.clickFormAuthenticationLink();
    }

    @Then("user is successfully logged in1")
    public void user_is_successfully_logged_in1() {
        System.out.println("user is successfully logged in");
        SecureAreaPage secureAreaPage = loginPage.logIn("tomsmith", "SuperSecretPassword!");

        Assert.assertEquals(secureAreaPage.getCurrentUrl(), secureAreaPage.getPageUrl());
        Assert.assertTrue(secureAreaPage.isLogOutButtonVisible(), "LogOut Button is not visible.");

    }
}

