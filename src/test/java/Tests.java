import org.junit.Test;

import feedbackapp.Feedback;
import feedbackapp.FeedbackRepository;
import static org.junit.Assert.*;

public class Tests {
	
    @Test
    public void testFeedbackClass() {
        Feedback fb = new Feedback();
        fb.setId(9999);
        fb.setName("Sergey Myasnikov");
        fb.setEmail("sergey@myasnikov@russia.ru");
        fb.setFeedback("This is a feedback service helthcheck");
        fb.setIsSpamAgreed(true);
        assertEquals("Wrong ID", fb.getId(), 9999);
        assertEquals("Wrong name", fb.getName(), "Sergey Myasnikov");
        assertEquals("Wrong email", fb.getEmail(), "sergey@myasnikov@russia.ru");
        assertEquals("Wrong feedback text", fb.getFeedback(), "This is a feedback service helthcheck");
        assertEquals("Wrong spam status", fb.getIsSpamAgreed(), true);
    }
    
    @Test
    public void testFeedbackRepository() {
    	Feedback fb1 = new Feedback();
    	Feedback fb2 = new Feedback();
    	
    	FeedbackRepository fr = FeedbackRepository.getInstance();
    	fr.put(fb1);
    	fr.put(fb2);
    	assertTrue("Wrond ID assignement", fb2.getId()-fb1.getId() == 1);
    }
}