package feedbackapp;

import java.util.*;

public class FeedbackRepository {
	
	private List<Feedback> feedbacks;
	private long count = 1;
	
	public FeedbackRepository() {
		this.feedbacks = new ArrayList<Feedback>();
	}
	
	public void put(Feedback feedback) {
		feedback.setId(count);
		this.feedbacks.add(feedback);
		count ++;
	}
	
	public List<Feedback> getList() {
		return this.feedbacks;	
	}

	public void remove(long id) {
		for (int i = 0; i < this.feedbacks.size(); i++) {
			if (feedbacks.get(i).getId()==id) {
				feedbacks.remove(i);
			}		
		}
	}

}
