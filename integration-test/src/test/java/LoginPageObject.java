import static org.junit.Assert.*;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import ru.yandex.qatools.allure.annotations.Step;


public class LoginPageObject {
	
	@Drone
	WebDriver driver;
    
    @FindBy(xpath = "//h1")
    private WebElement header;
    
    @FindBy(xpath = "//input[@name='username']")
    private WebElement usernameField;
    
    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordField;
    
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;
    
    @FindBy(xpath = "//p[contains(text(), 'Invalid username and password.')]")
    private WebElement error;
 
    @FindBy(xpath = "//p[contains(text(), 'You have been logged out.')]")
    private WebElement logoutMessage;

    
    /**
     * Method to open login page.
     */
    @Step("Open login page http://{0}/login")
    public void start(String host) {
    	driver.get("http://" + host + "/login");
    }
    
    /**
     * Verify login page title and header.
     */
    @Step("Verify login page title and header")
    public void verifyLoginPage() {
    	assertEquals("Wrong Page title", "Login - Feedback App", driver.getTitle());
    	assertEquals("Wrong header", "Please login", header.getText());
    }
    
    /**
     * Method to fill and submit login form.
     * 
     * @param username
     * @param password
     */
    @Step("Submit login with username={0} and password={1}")
    public void fillForm(String username, String password) {   	
    	usernameField.sendKeys(username);
    	passwordField.sendKeys(password);
    	submitButton.click();
    }
    
    /**
     * Verify the presence of login error
     * 
     */
    @Step("Verify login error")
    public void verifyError() {   	
    	try {
    		assertTrue("Name error is not present", error.isDisplayed());
		} catch (NoSuchElementException e) {
			assertTrue(e.getMessage(), false);
		}	
    }
    
    /**
     * Verify the presence of logout message
     * 
     */
    @Step("Verify logout message")
    public void verifyLogoutMessage() {   	
    	try {
    		assertTrue("Name error is not present", logoutMessage.isDisplayed());
		} catch (NoSuchElementException e) {
			assertTrue(e.getMessage(), false);
		}	
    }
    

}
