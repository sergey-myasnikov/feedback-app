/**
 * Simple Feedback object to store details about added feedbacks.
 *
 */
public class Feedback {
	
	long id;
	String name;
	String email;
	String feedback;
	boolean isSpamAgreed;
	
	/**
	 * Feedback constructor. Id is undefided at this point.
	 * 
	 * @param name
	 * @param email
	 * @param feedback
	 * @param isSpamAgreed
	 */
	public Feedback(String name, String email, String feedback, boolean isSpamAgreed) {
		this.name = name;
		this.email = email;
		this.feedback = feedback;
		this.isSpamAgreed = isSpamAgreed;
	}
	
	/**
	 * Set newly assigned id
	 * 
	 * @param id Assigned id
	 */
	public void setId(long id) {
		this.id = id;
	}

}
