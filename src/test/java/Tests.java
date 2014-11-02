import org.junit.BeforeClass;
import org.junit.Test;

import feedbackapp.Feedback;
import feedbackapp.FeedbackRepository;
import static org.junit.Assert.*;

public class Tests {
	
	private static Feedback fb1;
	private static Feedback fb2;
	private static FeedbackRepository fr;
	
	@BeforeClass
	public static void init() {
		fb1 = new Feedback();
		fb2 = new Feedback();		
		fr = FeedbackRepository.getInstance();	
	}
	
	
    @Test
    public void testFeedbackClass() {
        fb1.setId(9999);
        fb1.setName("Sergey Myasnikov");
        fb1.setEmail("sergey@myasnikov@russia.ru");
        fb1.setFeedback("This is a feedback service helthcheck");
        fb1.setIsSpamAgreed(true);
        assertEquals("Wrong ID", fb1.getId(), 9999);
        assertEquals("Wrong name", fb1.getName(), "Sergey Myasnikov");
        assertEquals("Wrong email", fb1.getEmail(), "sergey@myasnikov@russia.ru");
        assertEquals("Wrong feedback text", fb1.getFeedback(), "This is a feedback service helthcheck");
        assertEquals("Wrong spam status", fb1.getIsSpamAgreed(), true);
    }
    
    @Test
    public void testFeedbackRepository() {
    	fr.put(fb1);
    	fr.put(fb2);
    	assertTrue("Wrond ID assignement", fb2.getId()-fb1.getId() == 1);
    }
}