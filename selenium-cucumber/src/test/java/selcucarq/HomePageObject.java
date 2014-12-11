package selcucarq;

import static org.junit.Assert.assertTrue;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class HomePageObject {

	@Drone
	protected WebDriver browser;
	

	/**
	 * Open page before performing test.
	 * @param page
	 * @throws Throwable
	 */
	@Given("^the page is open \"([^\"]*)\"$")
	public void the_page_is_open(String page) throws Throwable {
		browser.get(page);
	}

	/**
	 * Open page during test.
	 * @param page
	 * @throws Throwable
	 */
	@When("^I open \"([^\"]*)\"$")
	public void open_the_page(String page) throws Throwable {
		browser.get(page);
	}
	
	/**
	 * Click menu button by xPath.
	 * @param button
	 * @throws Throwable
	 */
	@When("^I click \"([^\"]*)\" button$")
	public void click_button(String button) throws Throwable {
		browser.findElement(By.xpath("//input[@value='" + button + "']")).click();;
	}

	/**
	 * Verify browser tab title.
	 * @param title
	 * @throws Throwable
	 */
	@Then("^a browser title should contain \"([^\"]*)\"$")
	public void browser_title_should_contain(String title) throws Throwable {
		assertTrue(browser.getTitle().contains(title));
	}
	
	/**
	 * Verify browser URL.
	 * @param url
	 * @throws Throwable
	 */
	@Then("^a browser URL should be \"([^\"]*)\"$")
	public void browser_url_should_be(String url) throws Throwable {
		assertTrue(browser.getCurrentUrl().equalsIgnoreCase(url));
	}

	/**
	 * Verify page title.
	 * @param header
	 * @throws Throwable
	 */
	@Then("^a page should contain header \"([^\"]*)\"$")
	public void page_should_contain(String header) throws Throwable {
		assertTrue(browser.findElement(By.xpath("//h1")).getText().contains(header));
	}

}