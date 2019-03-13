package ug.co.sampletracker.app.models;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 7/26/2018.
 */
public class SpinnerItemData {

    String text;
    Integer imageId;

    public SpinnerItemData(String text, Integer imageId) {
        this.text = text;
        this.imageId = imageId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }
}
