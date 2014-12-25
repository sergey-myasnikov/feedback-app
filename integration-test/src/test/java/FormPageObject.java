import static org.junit.Assert.*;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import ru.yandex.qatools.allure.annotations.Step;


public class FormPageObject {
	
	@Drone
	WebDriver driver;
    
    @FindBy(xpath = "//h1")
    private WebElement header;
    
    @FindBy(xpath = "//input[@name='name']")
    private WebElement nameField;
    
    @FindBy(xpath = "//input[@name='email']")
    private WebElement emailField;
    
    @FindBy(xpath = "//textarea[@name='feedback']")
    private WebElement feedbackField;
    
    @FindBy(xpath = "//input[@name='isSpamAgreed']")
    private WebElement spamCheck;
    
    @FindBy(xpath = "//input[@value='Submit']")
    private WebElement submitButton;
    
    @FindBy(xpath = "//input[@value='Reset']")
    private WebElement resetButton;
    
    @FindBy(xpath = "//p[contains(text(),'your name')]")
    private WebElement nameError;
    
    @FindBy(xpath = "//p[contains(text(),'valid E-mail')]")
    private WebElement emailError;
    
    @FindBy(xpath = "//p[contains(text(),'2000')]")
    private WebElement feedbackError;
    
    @FindBy(xpath = "//p[@class='alert alert-error alert-fb')]")
    private WebElement error;
 
    /**
     * Method to open feedback form page
     */
    @Step("Open feedback form http://{0}/feedback")
    public void start(String host) {
    	driver.get("http://" + host + "/feedback");
    }
    
    /**
     * Verify feedback form page title and header
     */
    @Step("Verify feedback form title and header")
    public void verifyForm() {
    	assertEquals("Wrong Page title", "Post a Feedback - Feedback App", driver.getTitle());
    	assertEquals("Wrong header", "Post a Feedback", header.getText());
    }
    
    /**
     * Method to fill and submit form.
     * Note: isSpamAgreed check should not be set before using this.
     * 
     * @param name User name
     * @param email User e-mail
     * @param Feedback Feedback text
     * @param isSpamAgreed Spam checkbox state
     */
    @Step("Submit feedback: name={0}, e-mail={1}, feedback={2}, spam status={3}")
    public void fillForm(String name, String email, String Feedback, boolean isSpamAgreed) {
    	
    	nameField.sendKeys(name);
    	emailField.sendKeys(email);
    	feedbackField.sendKeys(Feedback);
    	if (isSpamAgreed) {
    		spamCheck.click();
		}
    	submitButton.click();
    }
    
    /**
     * Verify the presence of field validation errors.
     * 
     * @param errors An array of error codes: 1 for name error, 2 for e-mail error and 3 for feedback error
     */
    @Step("Verify errors by type(s) {0}")
    public void verifyError(int[] errors) {   	
    	try {
        	for (int i = 0; i < errors.length; i++) {
    			if (errors[i]==1) {
    		    	assertTrue("Name error is not present", nameError.isDisplayed());
    			} else if (errors[i]==2) {
    		    	assertTrue("E-mail error is not present", emailError.isDisplayed());
    			} else if (errors[i]==3) {
    		    	assertTrue("Feedback error is not present", feedbackError.isDisplayed());
    			}
    		}
		} catch (NoSuchElementException e) {
			assertTrue(e.getMessage(), false);
		}	
    }

}
