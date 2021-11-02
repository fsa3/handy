package is.hi.handy.Persistence.Entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity

@Table(name = "ads")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
    private String title;
    private String description;
    //pictures
    //location
    private Timestamp timePosted;
    private String trade;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Ad() {
        timePosted = new Timestamp(System.currentTimeMillis());
    }

    public Ad(String title, String description) {
        this.title = title;
        this.description = description;
        timePosted = new Timestamp(System.currentTimeMillis());
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

    public String getTrade() {
        return trade;
    }

    public void setTrade(String trade) {
        this.trade = trade;
    }

    public long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }
}
