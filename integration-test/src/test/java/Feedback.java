
public class Feedback {
	
	long id;
	String name;
	String email;
	String feedback;
	boolean isSpamAgreed;
	
	public Feedback(String name, String email, String feedback, boolean isSpamAgreed) {
		this.name = name;
		this.email = email;
		this.feedback = feedback;
		this.isSpamAgreed = isSpamAgreed;
	}
	
	public void setId(long id) {
		this.id = id;
	}

}
