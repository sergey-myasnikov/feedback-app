package selcucarq;

import static org.junit.Assert.assertTrue;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import cucumber.api.java.en.When;


public class FeedbackPageObject {

	@Drone
	protected WebDriver browser;
	
	String test;
	
	@When("^I enter name \"([^\"]*)\"$")
	public void enter_name(String name) throws Throwable {
		browser.findElement(By.xpath("//input[@name='name']")).sendKeys(name);
		test="test";
	}
	
	@When("^I enter e-mail \"([^\"]*)\"$")
	public void enter_email(String email) throws Throwable {
		browser.findElement(By.xpath("//input[@name='email']")).sendKeys(test);
	}
	
	@When("^I enter feedback \"([^\"]*)\"$")
	public void enter_feedback(String feedback) throws Throwable {
		browser.findElement(By.xpath("//textarea[@name='feedback']")).sendKeys(feedback);
	}

}