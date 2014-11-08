import static org.junit.Assert.*;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


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


    public void start() {
    	driver.get("http://fbgd.herokuapp.com");
    }
    
    public void verifyMain() {
    	assertEquals("Wrong Page title", "Home - Feedback App", driver.getTitle());
    	assertEquals("Wrong header", "Welcome to Feedback App", header.getText());
    }
    
    public void verifyLoginButton() {
    	assertTrue("No Login button", loginButton.isDisplayed());
    }
    
    public void verifyLogoutButton() {
    	assertTrue("No Logout button", logoutButton.isDisplayed());
    }
    
    public void clickMain() {
        homeButton.click();
    }
    
    public void clickPostFeedback() {
        postButton.click();
    }
	
    public void clickViewFeedbacks() {
        viewButton.click();
    }
    
    public void clickLogin() {
        loginButton.click();
    }
    
    public void clickLogout() {
        logoutButton.click();
    }

}
