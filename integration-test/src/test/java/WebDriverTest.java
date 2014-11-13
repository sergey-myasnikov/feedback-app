import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.spi.annotations.Page;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;


@RunWith(Arquillian.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WebDriverTest {
	
	private static List<Feedback> addedFeedbacks;
	private static Feedback fb1;
	private static Feedback fb2;
	private static Feedback fb3;
	private static String host;
 
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

    
    @BeforeClass
	public static void init() {
    	addedFeedbacks = new ArrayList<Feedback>();
    	fb1 = new Feedback("Web Driver 1", "web1@driver.com", "Feedback from WebDriver 1", true);
    	fb2 = new Feedback("Web Driver 2", "web2@driver.com", "Feedback from WebDriver 2", false);
    	fb3 = new Feedback("Web Driver 3", "web3@driver.com", "Feedback from WebDriver 3", true);
    	
    	host = System.getProperty("apphost");
    	if (host==null) {
			host ="fbgd.herokuapp.com";
		}
    	System.out.println("\n----> HOST: " + host + "\n");
	}
    
    @Test
    public void test01_MainPage_noLogin(){
    	main.start(host);
    	main.verifyMain();
    	main.verifyLoginButton();
    }
    
    @Test
    public void test02_NavigationToFeedbackForm_noLogin(){
    	main.start(host);
    	main.clickPostFeedback();
    	main.verifyLoginButton();
    	form.verifyForm();
    }
    
    @Test
    public void test03_NameValidation(){
    	form.start(host);
    	form.fillForm("", "web@driver.com", "Feedback from WebDriver", true);
    	form.verifyError(new int[] {1});
    }
    
    @Test
    public void test03_EmailValidation(){
    	form.start(host);
    	form.fillForm("Web Driver", "", "Feedback from WebDriver", true);
    	form.verifyError(new int[] {2});
    }
    
    @Test
    public void test03_FeedbackValidation(){
    	form.start(host);
    	form.fillForm("Web Driver", "web@driver.com", "", true);
    	form.verifyError(new int[] {3});
    }
    
    @Test
    public void test03_AllValidations(){
    	form.start(host);
    	form.fillForm("", "", "", false);
    	form.verifyError(new int[] {1,2,3});
    }
    
    @Test
    public void test04_1_SendFeedback_noLogin(){   	    	
    	form.start(host);
    	form.fillForm(fb1.name, fb1.email, fb1.feedback, fb1.isSpamAgreed);
    	int id = result.verifyResult(fb1.name, fb1.email, fb1.feedback);
    	
    	if (id != 0) {
    		fb1.setId(id);
    		addedFeedbacks.add(fb1);
		}
    }
    
    @Test
    public void test04_2_SendFeedback_noLogin(){
    	form.start(host);
    	form.fillForm(fb2.name, fb2.email, fb2.feedback, fb2.isSpamAgreed);
    	int id = result.verifyResult(fb2.name, fb2.email, fb2.feedback);
    	
    	if (id != 0) {
    		fb2.setId(id);
    		addedFeedbacks.add(fb2);
		}
    }
    
    @Test
    public void test04_3_SendFeedback_noLogin(){
    	form.start(host);
    	form.fillForm(fb3.name, fb3.email, fb3.feedback, fb3.isSpamAgreed);
    	int id = result.verifyResult(fb3.name, fb3.email, fb3.feedback);
    	
    	if (id != 0) {
    		fb3.setId(id);
    		addedFeedbacks.add(fb3);
		}
    }
    
    @Test
    public void test05_viewFeedbacks_noLogin(){
    	admin.start(host);
    	login.verifyLoginPage();
    	main.verifyLoginButton();
    }
    
    @Test
    public void test06_LoginError_noLogin(){
    	login.start(host);
    	login.verifyLoginPage();
    	login.fillForm("wrong","wrong");
    	login.verifyLoginPage();
    	login.verifyError();
    	main.verifyLoginButton();
    }
    
    @Test
    public void test07_Login(){
    	login.start(host);
    	login.verifyLoginPage();
    	login.fillForm("test","test");
    	admin.verifyAdminPage(); //redirection to admin page because of test 5
    	main.verifyLogoutButton();
    }
    
    @Test
    public void test08_viewFeedbacks_Login(){
    	admin.start(host);
    	admin.verifyAdminPage();
    	admin.verifyFeedbacks(addedFeedbacks);
    }
    
    @Test
    public void test09_deleteFeedbacks_Login(){
    	admin.start(host);
    	admin.verifyAdminPage();  	
    	//admin.deleteFeedback(id);   	
    	admin.deleteFeedbacks(addedFeedbacks);
    	admin.verifyFeedbacksNotPresent(addedFeedbacks);
    	addedFeedbacks.clear();
    }
    
    @Test
    public void test10_Logout(){
    	main.start(host);
    	main.verifyLogoutButton();
    	main.clickLogout();
    	login.verifyLoginPage();
    	login.verifyLogoutMessage();
    	main.verifyLoginButton();
    }
    
}