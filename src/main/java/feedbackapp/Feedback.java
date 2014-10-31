package feedbackapp;

/**
 * Feedback representation class with fields and accessors for 
 * the id, name, e-mail, feedback content and spam agreement data
 *
 */
public class Feedback {

	private long id;
    private String name;
    private String email;
    private String feedback;
    private boolean isSpamAgreed;

    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getFeedback() {
        return feedback;
    }
    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
    
    public boolean getIsSpamAgreed() {
        return isSpamAgreed;
    }
    
    public void setIsSpamAgreed(boolean isSpamAgreed) {
        this.isSpamAgreed = isSpamAgreed;
    }

}
