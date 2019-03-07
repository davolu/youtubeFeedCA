package davolusoft.com.youtubefeedca.model;

/**
 * Created by David Oluyale on 3/6/2019.
 * POJO
 */

public class Videos {

    private int id;
    private String name;
    private String imageURL;
    private String numberOfViews;
    private String profileImageURL;

    public Videos(int id, String name, String imageURL, String numberOfViews, String profileImageURL) {
        this.id = id;
        this.name = name;
        this.imageURL = imageURL;
        this.numberOfViews = numberOfViews;
        this.profileImageURL = profileImageURL;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setNumberOfViews(String numberOfViews) {
        this.numberOfViews = numberOfViews;
    }

    public void setProfileImageURL(String profileImageURL) {
        this.profileImageURL = profileImageURL;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getNumberOfViews() {
        return numberOfViews;
    }

    public String getProfileImageURL() {
        return profileImageURL;
    }
}
