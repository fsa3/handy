package is.hi.handy.Persistence.Entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "portfolioItem")
public class PortfolioItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private String title;
    private String description;
    // Bætti við location
    private String location;
    //private List<???> pictures;

    @ManyToOne(fetch = FetchType.LAZY)
    private HandyUser handyman;

    public PortfolioItem() {
    }

    public PortfolioItem(String title, String description, String location, HandyUser handyman) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.handyman = handyman;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public HandyUser getHandyman() {
        return handyman;
    }

    public void setHandyman(HandyUser handyman) {
        this.handyman = handyman;
    }
}
