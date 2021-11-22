package is.hi.handy.Persistence.Entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Entity

@Table(name = "ads")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
    private String title;
    private String description;
    @OneToOne
    private Image image;
    private String stringImage;
    private String location;
    private Timestamp timePosted;
    private String formattedDate;
    private Trade trade;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Ad() {
        timePosted = new Timestamp(System.currentTimeMillis());
        formattedDate = new SimpleDateFormat("dd.MM.yyyy").format(timePosted);
    }

    public Ad(String title, String description) {
        this.title = title;
        this.description = description;
        timePosted = new Timestamp(System.currentTimeMillis());
        formattedDate = new SimpleDateFormat("dd.MM.yyyy").format(timePosted);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getTimePosted() {
        return timePosted;
    }

    public void setTimePosted(Timestamp timePosted) {
        this.timePosted = timePosted;
    }

    public Trade getTrade() {
        return trade;
    }

    public void setTrade(Trade trade) {
        this.trade = trade;
    }

    public long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getStringImage() {
        return stringImage;
    }

    public void setStringImage(String stringImage) {
        this.stringImage = stringImage;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFormattedDate() {
        return formattedDate;
    }
}
