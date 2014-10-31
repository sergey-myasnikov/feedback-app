package feedbackapp;

import java.util.*;

/**
 * Class to keep in-memory a collection of Feedbacks.
 * Should be replaced with DB layer!
 *
 */
public class FeedbackRepository {
	
	private List<Feedback> feedbacks;
	private long count = 1;
	

	public FeedbackRepository() {
		this.feedbacks = new ArrayList<Feedback>();
	}
	
	public List<Feedback> getList() {
		return this.feedbacks;	
	}
	
	/**
	 * Assign an ID to Feedback and add a Feedback object to repository.
	 * @param feedback Feedback object
	 */
	public void put(Feedback feedback) {
		feedback.setId(count);
		this.feedbacks.add(feedback);
		count ++;
	}
	
	/**
	 * Remove Feedback(s) from repository by ID.
	 * @param id Feedback ID
	 */
	public void remove(long id) {
		for (int i = 0; i < this.feedbacks.size(); i++) {
			if (feedbacks.get(i).getId()==id) {
				feedbacks.remove(i);
			}		
		}
	}

}
