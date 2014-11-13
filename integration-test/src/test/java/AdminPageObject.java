import static org.junit.Assert.*;

import java.util.List;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class AdminPageObject {
	
	@Drone
	WebDriver driver;
    
    @FindBy(xpath = "//h1")
    private WebElement header;
    
    
    /**
     * Method to open admin page
     */
    public void start(String host) {
    	driver.get("http://" + host + "/admin");
    }
    
    /**
     * Verify feedback form page title and header
     */
    public void verifyAdminPage() {
    	assertEquals("Wrong Page title", "All Feedbacks - Feedback App", driver.getTitle());
    	assertEquals("Wrong header", "All Feedbacks", header.getText());
    }
     
    /**
     * Verify presence of feedbacks in the list and feedback details.
     * 
     * @param addedFeedbacks List of previously added feedbacks
     */
    public void verifyFeedbacks(List<Feedback> addedFeedbacks) {
    	
    	for (int i = 0; i < addedFeedbacks.size(); i++) {
    		
			long id = addedFeedbacks.get(i).id; //will search table elements by id
			
			List<WebElement> feedbacks = driver.findElements(By.xpath("//td[@id='id' and contains(text(),'" + id + "')]"));
			assertTrue("Fedback with id " + id + " is not found", feedbacks.size() > 0); //found element
			assertEquals("Several Fedbacks with id " + id + " is not found", 1, feedbacks.size()); //the only one
			
			//check name
			assertEquals("Wrong name",
					addedFeedbacks.get(i).name,
					driver.findElement(By.xpath("//td[@id='id' and contains(text(),'" + id + "')]/../td[@id='name']")).getText());
			//check e-mail
			assertEquals("Wrong e-mail",
					addedFeedbacks.get(i).email,
					driver.findElement(By.xpath("//td[@id='id' and contains(text(),'" + id + "')]/../td[@id='email']")).getText());
			//check feedback text
			assertEquals("Wrong feedback",
					addedFeedbacks.get(i).feedback,
					driver.findElement(By.xpath("//td[@id='id' and contains(text(),'" + id + "')]/../td[@id='feedback']")).getText());
			//check spam status
			boolean isChecked = false;
			try {
				if (driver
						.findElement(By.xpath("//td[@id='id' and contains(text(),'" + id + "')]/../td[@id='spam']/input"))
						.getAttribute("checked").equalsIgnoreCase("true")) {
					isChecked = true;				
				}		
			} catch (Exception e) {
				//ignore
			}
			assertEquals("Wrong spam status", addedFeedbacks.get(i).isSpamAgreed, isChecked);	
		}	
    }
    
    /**
     * Verify feedbacks are not present
     * 
     * @param addedFeedbacks A list of feedbacks which were removed
     */
    public void verifyFeedbacksNotPresent(List<Feedback> addedFeedbacks) {
    	for (int i = 0; i < addedFeedbacks.size(); i++) {
    		
			long id = addedFeedbacks.get(i).id; //will search table elements by id
			
			List<WebElement> feedbacks = driver.findElements(By.xpath("//td[@id='id' and contains(text(),'" + id + "')]"));
			assertTrue("Fedback with id " + id + " is found", feedbacks.size() == 0);	
		}   	
    }
    
    /**
     * Delete a single feedback by id
     * 
     * @param id Feedback id
     */
    public void deleteFeedback(long id) {
    	
		List<WebElement> feedbacks = driver.findElements(By.xpath("//td[@id='id' and contains(text(),'" + id + "')]"));
		assertTrue("Fedback with id " + id + " is not found", feedbacks.size() > 0); //found element
		assertEquals("Several Fedbacks with id " + id + " is not found", 1, feedbacks.size()); //the only one
    	
		WebElement deleteButton = driver
				.findElement(By.xpath("//td[@id='id' and contains(text(),'" + id + "')]/../td[@id='delete']//input[@value='Delete']"));
		deleteButton.click();
		driver.switchTo().alert().accept();
		
    }
    
    /**
     * Delete a number of Feedbacks
     * 
     * @param addedFeedbacks A list of Feedbacks to delete
     */
    public void deleteFeedbacks(List<Feedback> addedFeedbacks) {
    	for (int i = 0; i < addedFeedbacks.size(); i++) { 		
			long id = addedFeedbacks.get(i).id; //will delete table elements by id
			deleteFeedback(id);	
    	}
    }

}
