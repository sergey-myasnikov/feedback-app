import java.util.ArrayList;
import java.util.List;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.page.Page;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;

import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;


@RunWith(Arquillian.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Description("Smoke test of Feedback application")
public class WebDriverTest {
	
	private static List<Feedback> addedFeedbacks;
	private static Feedback fb1;
	private static Feedback fb2;
	private static Feedback fb3;
	private static String host;
 
    @Drone
    static WebDriver driver;

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
    
    @AfterClass
    public static void teardown() {
    	driver.quit();
    }
    
    @Test
    @Title("01-1 Open main page")
    @Features("Main page")
    @Stories("As a user open main page")
    @Description("As a user open main page and verify page URL, title, header and the presence of 'Login' button")
    public void test01_1_MainPage_noLogin(){
    	driver.manage().window().maximize(); //maximize before execution
    	main.start(host);
    	main.verifyMain(host);
    	main.verifyLoginButton();
    }
    
    @Test
    @Title("01-2 Open main page by aliases")
    @Features("Main page")
    @Stories("As a user open main page aliases")
    @Description("As a user open main page aliases and verify page URL, title, header and the presence of 'Login' button for each")
    public void test01_2_MainPage_noLogin(){
    	main.start_home(host);
    	main.verifyMain(host);
    	main.verifyLoginButton();
    	
    	main.start_main(host);
    	main.verifyMain(host);
    	main.verifyLoginButton();
    	
    	main.start_index(host);
    	main.verifyMain(host);
    	main.verifyLoginButton();
    }
  
    @Test
    @Title("02-1 Open Feedback form")
    @Features("Feedback form")
    @Stories("As a user open Feedback form by URL")
    @Description("As a user open Feedback form by URL and verify title, header and the presence of 'Login' button")
    public void test02_1_NavigationToFeedbackForm_noLogin(){
    	form.start(host);
    	main.verifyLoginButton();
    	form.verifyForm();
    }
    
    @Test
    @Title("02-2 Navigare to Feedback form")
    @Features("Feedback form")
    @Stories("As a user open Feedback form via 'Post a Feedback' button")
    @Description("As a user open Feedback form via 'Post a Feedback' button and verify title, header and the presence of 'Login' button")
    public void test02_2_NavigationToFeedbackForm_noLogin(){
    	main.start(host);
    	main.clickPostFeedback();
    	main.verifyLoginButton();
    	form.verifyForm();
    }
    
    @Test
    @Title("03-1 Post Feedback with empty name")
    @Features({"Feedback form", "Negative Scenario"})
    @Stories("Empty name")
    @Description("As a user open Feedback form by URL and post a Feedback with empty name, veryfy error message")
    public void test03_NameValidation(){
    	form.start(host);
    	form.fillForm("", "web@driver.com", "Feedback from WebDriver", true);
    	form.verifyError(new int[] {1});
    }
    
    //Test wrong, but not empty e-mail
    
    @Test
    @Title("03-2 Post Feedback with empty name")
    @Features({"Feedback form", "Negative Scenario"})
    @Description("As a user open Feedback form by URL and post a Feedback with empty e-mail, veryfy error message")
    @Stories("Empty e-mail")
    public void test03_EmailValidation(){
    	form.start(host);
    	form.fillForm("Web Driver", "", "Feedback from WebDriver", true);
    	form.verifyError(new int[] {2});
    }
    
    @Test
    @Title("03-3 Post Feedback with empty feedback field")
    @Features({"Feedback form", "Negative Scenario"})
    @Stories("Empty Feedback")
    @Description("As a user open Feedback form by URL and post a Feedback with empty feedback field, veryfy error message")
    public void test03_FeedbackValidation(){
    	form.start(host);
    	form.fillForm("Web Driver", "web@driver.com", "", true);
    	form.verifyError(new int[] {3});
    }
    
    @Test
    @Title("03-4 Post an empty Feedback form")
    @Features({"Feedback form", "Negative Scenario"})
    @Stories("Empty Feedback form")
    @Description("As a user open Feedback form by URL and post an empty form, veryfy error message")
    public void test03_AllValidations(){
    	form.start(host);
    	form.fillForm("", "", "", false);
    	form.verifyError(new int[] {1,2,3});
    }
    
    @Test
    @Title("04-1 Post a Feedback")
    @Features("Feedback form")
    @Stories("Post a Feedback 1")
    @Description("As a user open Feedback form by URL and post a valid Feedback, veryfy name, e-mail, amd feedback text on a result page")
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
    @Title("04-2 Post a Feedback")
    @Features("Feedback form")
    @Stories("Post a Feedback 2")
    @Description("As a user open Feedback form by URL and post a valid Feedback, veryfy name, e-mail, amd feedback text on a result page")
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
    @Title("04-3 Post a Feedback")
    @Features("Feedback form")
    @Stories("Post a Feedback 3")
    @Description("As a user open Feedback form by URL and post a valid Feedback, veryfy name, e-mail, amd feedback text on a result page")
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
    @Title("05 Open Admin page without login")
    @Features({"Admin page", "Login page"})
    @Stories("Redirect to Login")
    @Description("As a user open Admin page URL, veryfy redirection to Login page and the presence of 'Login' button")
    public void test05_viewFeedbacks_noLogin(){
    	admin.start(host);
    	login.verifyLoginPage();
    	main.verifyLoginButton();
    }
    
    @Test
    @Title("06 Wrong login credentials")
    @Features({"Login page", "Negative Scenario"})
    @Stories("Enter wrong login credentials")
    @Description("As a user open Login page URL and verify page title and header; enter worng credentials, click 'Login' and verify page title, header, error message and the presence of 'Login' button")
    public void test06_LoginError_noLogin(){
    	login.start(host);
    	login.verifyLoginPage();
    	login.fillForm("wrong","wrong");
    	login.verifyLoginPage();
    	login.verifyError();
    	main.verifyLoginButton();
    }
    
    @Test
    @Title("07 Login and redirect to Admin page")
    @Features({"Admin page", "Login page"})
    @Stories("Login and redirect to Admin page")
    @Description("As a user open Login page URL and verify page title and header; enter valid credentials, click 'Login' and verify redirection to 'Admin' page and the presence of 'Logout' button")
    public void test07_Login(){
    	login.start(host);
    	login.verifyLoginPage();
    	login.fillForm("test","test");
    	admin.verifyAdminPage(); //redirection to admin page because of test 5
    	main.verifyLogoutButton();
    }
    
    @Test
    @Title("08 Open Admin page")
    @Features("Admin page")
    @Stories("Open Admin page")
    @Description("As an admin open Admin page URL and verify page title, header and the presense of previouly submitted feedbacks")
    public void test08_viewFeedbacks_Login(){
    	admin.start(host);
    	admin.verifyAdminPage();
    	admin.verifyFeedbacks(addedFeedbacks);
    }
    
    @Test
    @Title("09 Delete Feedbacks")
    @Features("Admin page")
    @Stories("Delete Feedbacks")
    @Description("As an admin open Admin page URL and verify page title and header, delete added feedbacks and verify they are not present animore")
    public void test09_deleteFeedbacks_Login(){
    	admin.start(host);
    	admin.verifyAdminPage();  	
    	//admin.deleteFeedback(id);   	
    	admin.deleteFeedbacks(addedFeedbacks);
    	admin.verifyFeedbacksNotPresent(addedFeedbacks);
    	addedFeedbacks.clear();
    }
    
    @Test
    @Title("10 Logout")
    @Features({"Main page", "Login page"})
    @Stories("Logout")
    @Description("As an admin open Main page URL and verify the presense of 'Logout' button, click 'Logout' and verify redirection to 'Login' page, message and the presense of 'Login' button")
    public void test10_Logout(){
    	main.start(host);
    	main.verifyLogoutButton();
    	main.clickLogout();
    	login.verifyLoginPage();
    	login.verifyLogoutMessage();
    	main.verifyLoginButton();
    }
    
}