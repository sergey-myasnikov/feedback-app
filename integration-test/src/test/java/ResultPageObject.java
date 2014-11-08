import static org.junit.Assert.*;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class ResultPageObject {
	
	private final String GREETING_LINE = "Hi ";
	private final String ID_LINE = "Thank you for the feedback. It was assigned an ID ";
	private final String EMAIL_LINE = "We'll reply you asap to ";
	
	@Drone
	WebDriver driver;
    
    @FindBy(xpath = "//h1")
    private WebElement header;
    
    @FindBy(xpath = "//p[contains(text(),'Hi')]")
    private WebElement nameBlock;
    
    @FindBy(xpath = "//p[contains(text(),'Thank you')]")
    private WebElement idBlock;
    
    @FindBy(xpath = "//p[contains(text(),'reply you asap')]")
    private WebElement emailBlock;
    
    @FindBy(xpath = "//p[@id='fb']")
    private WebElement feedbackBlock;


    /**
     * Method to verify feedback details on a result page.
     * 
     * @param name User name used in post form
     * @param email User e-mail used in post form
     * @param feedback Feedback text
     * @return id of a new feedback
     */
    public int verifyResult( String name, String email, String feedback) {
    	assertEquals("Wrong Page title", "Success - Feedback App", driver.getTitle());
    	assertEquals("Wrong header", "Feedback successfully sent!", header.getText());
    	assertEquals("Wrong Name", name, nameBlock.getText().replace(GREETING_LINE, "").replace(",", ""));
    	
    	int id = 0;
    	
    	try {
    		id = Integer.parseInt(idBlock.getText().substring(ID_LINE.length()));
    		assertNotNull(id);
    		assertNotEquals(id, 0);
    	} catch ( NumberFormatException e) {
    		assertTrue("Cannot get feedback ID", false);
    	}
 
    	assertEquals("Wrong Name", email, emailBlock.getText().substring(EMAIL_LINE.length()));
    	assertEquals("Wrong Feedback", feedback, feedbackBlock.getText());
    	
    	return id;
    }

}
