package selcucarq;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import cucumber.api.CucumberOptions;
import cucumber.runtime.arquillian.ArquillianCucumber;
import cucumber.runtime.arquillian.api.Features;
import cucumber.runtime.arquillian.api.Glues;


@Glues({HomePageObject.class, FeedbackPageObject.class})
@Features({"01_home-page.feature", "02_feedback-page.feature"})
@CucumberOptions(format = {"pretty", "html:target/cucumber", "json:target/cucumber.json"})
@RunWith(ArquillianCucumber.class)
public class _SelCucArqTest {
 
    // just because drone extension check test class only for injections. Without any it doesn't start the context
    // will be fixed with a next version
    @Drone
    private WebDriver selenium;
}