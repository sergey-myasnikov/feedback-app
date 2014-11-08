import static org.junit.Assert.*;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


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
    public void start() {
    	driver.get("http://fbgd.herokuapp.com/login");
    }
    
    /**
     * Verify login page title and header.
     */
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
    public void fillForm(String username, String password) {   	
    	usernameField.sendKeys(username);
    	passwordField.sendKeys(password);
    	submitButton.click();
    }
    
    /**
     * Verify the presence of login error
     * 
     */
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
    public void verifyLogoutMessage() {   	
    	try {
    		assertTrue("Name error is not present", logoutMessage.isDisplayed());
		} catch (NoSuchElementException e) {
			assertTrue(e.getMessage(), false);
		}	
    }
    

}
