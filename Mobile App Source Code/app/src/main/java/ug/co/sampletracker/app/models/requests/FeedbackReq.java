package ug.co.sampletracker.app.models.requests;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 10/24/2018.
 */
public class FeedbackReq {

    private String customerReference;
    private String feedbackType;
    private String feedback;
    private double rating;

    public String getCustomerReference() {
        return customerReference;
    }

    public void setCustomerReference(String customerReference) {
        this.customerReference = customerReference;
    }

    public String getFeedbackType() {
        return feedbackType;
    }

    public void setFeedbackType(String feedbackType) {
        this.feedbackType = feedbackType;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
