package feedbackapp;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

/**
 * Spring controller class
 *
 */
@Controller
public class FeedbackController {
	
	private FeedbackRepository repository = new FeedbackRepository();

    @RequestMapping(value="/main")
    public String mainPage(Model model) {
    	model.addAttribute("li", isLoggedIn());
    	return "main";
    }
	
    @RequestMapping(value={"/", "/index", "/home"})
    public String mainPageRedirect(Model model) {
    	return "redirect:main";
    }
    
	
    @RequestMapping(value="/feedback", method = RequestMethod.GET)
    public String feedbackForm(Model model) {
    	model.addAttribute("li", isLoggedIn());
        model.addAttribute("feedback", new Feedback());
        return "feedback";
    }

    @RequestMapping(value="/feedback", method = RequestMethod.POST)
    public String feedbackSubmit(@ModelAttribute Feedback feedback, Model model) {
    	model.addAttribute("li", isLoggedIn());
        repository.put(feedback);
        return "result";
    }
    
    @RequestMapping(value="/admin", method = RequestMethod.GET)
    public String adminForm(Model model) {  	
        List<Feedback> testList = repository.getList();
    	model.addAttribute("li", isLoggedIn());
        model.addAttribute("testList", testList);
        return "admin";
    }
    
    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public String removeFeedback(@RequestParam("id") long id) {
        repository.remove(id);
        return "redirect:/admin";
    }
      
    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String login(Model model) {
    	model.addAttribute("li", isLoggedIn());
    	return "login";    
    }
    
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logout(Model model) {
    	SecurityContextHolder.clearContext();
    	model.addAttribute("li", isLoggedIn());
    	return "main";
    }
    
    private boolean isLoggedIn(){
    	if (SecurityContextHolder.getContext().getAuthentication() == null  ||
    			SecurityContextHolder.getContext().getAuthentication()
    			.getName().equalsIgnoreCase("anonymousUser")) {
    		return false; 		
		}
    	return true;   	
    }
}