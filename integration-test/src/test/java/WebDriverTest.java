import java.util.ArrayList;
import java.util.List;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.spi.annotations.Page;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;

@RunWith(Arquillian.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WebDriverTest {
	
	private List<Feedback> addedFeedbacks = new ArrayList<Feedback>();
 
    @Drone
    WebDriver driver;

    @Page
    private MainPageObject main;
    
    @Page
    private FormPageObject form;
    
    @Page
    private ResultPageObject result;
    
    @Page
    private LoginPageObject login;
    
    @Page
    private AdminPageObject admin;

    
    @Test
    public void test01_MainPage_noLogin(){
    	main.start();
    	main.verifyMain();
    	main.verifyLoginButton();
    }
    
    @Test
    public void test02_NavigationToFeedbackForm_noLogin(){
    	main.start();
    	main.clickPostFeedback();
    	main.verifyLoginButton();
    	form.verifyForm();
    }
    
    @Test
    public void test03_NameValidation(){
    	form.start();
    	form.fillForm("", "web@driver.com", "Feedback from WebDriver", true);
    	form.verifyError(new int[] {1});
    }
    
    @Test
    public void test03_EmailValidation(){
    	form.start();
    	form.fillForm("Web Driver", "", "Feedback from WebDriver", true);
    	form.verifyError(new int[] {2});
    }
    
    @Test
    public void test03_FeedbackValidation(){
    	form.start();
    	form.fillForm("Web Driver", "web@driver.com", "", true);
    	form.verifyError(new int[] {3});
    }
    
    @Test
    public void test03_AllValidations(){
    	form.start();
    	form.fillForm("", "", "", false);
    	form.verifyError(new int[] {1,2,3});
    }
    
    @Test
    public void test04_1_SendFeedback_noLogin(){
    	
    	Feedback fb = new Feedback("Web Driver 1", "web1@driver.com", "Feedback from WebDriver 1", true);
    	
    	form.start();
    	form.fillForm(fb.name, fb.email, fb.feedback, fb.isSpamAgreed);
    	int id = result.verifyResult(fb.name, fb.email, fb.feedback);
    	
    	if (id != 0) {
    		fb.setId(id);
    		addedFeedbacks.add(fb);
		}
    }
    
    @Test
    public void test04_2_SendFeedback_noLogin(){
    	
    	Feedback fb = new Feedback("Web Driver 2", "web2@driver.com", "Feedback from WebDriver 2", true);
    	
    	form.start();
    	form.fillForm(fb.name, fb.email, fb.feedback, fb.isSpamAgreed);
    	int id = result.verifyResult(fb.name, fb.email, fb.feedback);
    	
    	if (id != 0) {
    		fb.setId(id);
    		addedFeedbacks.add(fb);
		}
    }
    
    @Test
    public void test04_3_SendFeedback_noLogin(){
    	
    	Feedback fb = new Feedback("Web Driver 3", "web3@driver.com", "Feedback from WebDriver 3", true);
    	
    	form.start();
    	form.fillForm(fb.name, fb.email, fb.feedback, fb.isSpamAgreed);
    	int id = result.verifyResult(fb.name, fb.email, fb.feedback);
    	
    	if (id != 0) {
    		fb.setId(id);
    		addedFeedbacks.add(fb);
		}
    }
    
    @Test
    public void test05_viewFeedbacks_noLogin(){
    	admin.start();
    	login.verifyLoginPage();
    	main.verifyLoginButton();
    }
    
    @Test
    public void test06_LoginError_noLogin(){
    	login.start();
    	login.verifyLoginPage();
    	login.fillForm("wrong","wrong");
    	login.verifyLoginPage();
    	login.verifyError();
    	main.verifyLoginButton();
    }
    
    @Test
    public void test07_Login(){
    	login.start();
    	login.verifyLoginPage();
    	login.fillForm("test","test");
    	admin.verifyAdminPage(); //redirection to admin page because of test 5
    	main.verifyLogoutButton();
    }
    
    @Test
    public void test08_viewFeedbacks_Login(){
    	admin.start();
    	admin.verifyAdminPage();
    	admin.verifyFeedbacks(addedFeedbacks);
    }
    
    @Test
    public void test09_deleteFeedbacks_Login(){
    	admin.start();
    	admin.verifyAdminPage();  	
    	//admin.deleteFeedback(id);   	
    	admin.deleteFeedbacks(addedFeedbacks);
    	admin.verifyFeedbacksNotPresent(addedFeedbacks);
    	addedFeedbacks.clear();
    }
    
    @Test
    public void test10_Logout(){
    	main.start();
    	main.verifyLogoutButton();
    	main.clickLogout();
    	login.verifyLoginPage();
    	login.verifyLogoutMessage();
    	main.verifyLoginButton();
    }
    
}