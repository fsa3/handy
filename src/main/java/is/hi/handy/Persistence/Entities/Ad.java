package is.hi.handy.Persistence.Entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ads")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    private String title;
    private String description;
    //pictures
    //location
    private Timestamp timestap;
    private String trade;

    public Ad() {
    }

    public Ad(String title, String description) {
        this.title = title;
        this.description = description;
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

    public Timestamp getTimestap() {
        return timestap;
    }

    public void setTimestap(Timestamp timestap) {
        this.timestap = timestap;
    }

    public String getTrade() {
        return trade;
    }

    public void setTrade(String trade) {
        this.trade = trade;
    }
}