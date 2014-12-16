import static org.junit.Assert.*;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import ru.yandex.qatools.allure.annotations.Step;


public class MainPageObject {
	
	@Drone
	WebDriver driver;

    @FindBy(xpath = "//input[@value='Home']")
    private WebElement homeButton;
    
    @FindBy(xpath = "//input[@value='Post a Feedback']")
    private WebElement postButton;
    
    @FindBy(xpath = "//input[@value='View all Feedbacks']")
    private WebElement viewButton;
    
    @FindBy(xpath = "//input[@value='Login']")
    private WebElement loginButton;
    
    @FindBy(xpath = "//input[@value='Logout']")
    private WebElement logoutButton;
    
    @FindBy(xpath = "//h1")
    private WebElement header;

    @Step("Open home page http://{0}")
    public void start(String host) {
    	driver.get("http://" + host);
    }
    
    @Step("Open home page alias http://{0}/home")
    public void start_home(String host) {
    	driver.get("http://" + host);
    }
    
    @Step("Open home page alias http://{0}/main")
    public void start_main(String host) {
    	driver.get("http://" + host + "/main");
    }
    
    @Step("Open home page alias http://{0}/index")
    public void start_index(String host) {
    	driver.get("http://" + host + "/index");
    }
    
    @Step("Verify home page URL, title and header")
    public void verifyMain() {
    	assertEquals("Wrong Page URL", "http://fbgd.herokuapp.com/main", driver.getCurrentUrl());
    	assertEquals("Wrong Page title", "Home - Feedback App", driver.getTitle());
    	assertEquals("Wrong header", "Welcome to Feedback App", header.getText());
    }
    
    @Step("Verify 'Login' button is present")
    public void verifyLoginButton() {
    	assertTrue("No Login button", loginButton.isDisplayed());
    }
    
    @Step("Verify 'Logout' button is present")
    public void verifyLogoutButton() {
    	assertTrue("No Logout button", logoutButton.isDisplayed());
    }
    
    @Step("Click 'Home' button")
    public void clickMain() {
        homeButton.click();
    }
    
    @Step("Click 'Post a Feedback' button")
    public void clickPostFeedback() {
        postButton.click();
    }
    
    @Step("Click 'view all Feedbacks' button")
    public void clickViewFeedbacks() {
        viewButton.click();
    }
    
    @Step("Click 'Login' button")
    public void clickLogin() {
        loginButton.click();
    }
    
    @Step("Click 'Logout' button")
    public void clickLogout() {
        logoutButton.click();
    }

}
