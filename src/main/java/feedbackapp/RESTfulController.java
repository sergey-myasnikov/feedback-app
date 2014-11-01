package feedbackapp;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RESTfulController {
	
	private FeedbackRepository repository = new FeedbackRepository();
	
    @RequestMapping(value="/api", method = RequestMethod.GET)
    public Feedback helthCheck() {
        Feedback fb = new Feedback();
        fb.setId(9999);
        fb.setName("Sergey Myasnikov");
        fb.setEmail("sergey@myasnikov@russia.ru");
        fb.setFeedback("This is a feedback service helthcheck");
        fb.setIsSpamAgreed(true);
    	return fb;
    }
	
    @RequestMapping(value="/api/post", method = RequestMethod.POST)
    public Feedback postFeedback(@RequestBody Feedback fb) {
    	repository.put(fb);
        return fb;
    }
    
    @RequestMapping(value="/api/get" , method = RequestMethod.GET)
    public List<Feedback> getFeedbackList() {
        return repository.getList();
    }
    
    @RequestMapping(value="/api/get" , method = RequestMethod.DELETE)
    public List<Feedback> deleteFeedback(@RequestParam long id) {
    	repository.remove(id);
        return repository.getList();
    }

}
