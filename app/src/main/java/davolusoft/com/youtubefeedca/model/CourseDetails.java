package davolusoft.com.youtubefeedca.model;

/**
 * Created by David Oluyale on 3/6/2019.
 * POJO
 */

public class CourseDetails {
    String number;
    String name;
    String duration;
    String imageUrl;
    String link;


    public CourseDetails(String  number, String name, String duration, String imageUrl, String link) {
        this.number = number;
        this.name = name;
        this.duration = duration;
        this.imageUrl = imageUrl;
        this.link = link;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
