package selcucarq;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.runtime.arquillian.ArquillianCucumber;
import cucumber.runtime.arquillian.api.Features;
import cucumber.runtime.arquillian.api.Glues;


@Glues({HomePageObject.class, FeedbackPageObject.class})
@Features({"01_home-page.feature", "02_feedback-page.feature"})
@CucumberOptions(format = {"pretty", "html:target/cucumber", "json:target/cucumber.json"})
@RunWith(ArquillianCucumber.class)
public class _SelCucArqTest {
	
//    @Drone
//    private WebDriver selenium;
    
}