package is.hi.handy.Persistence.Entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "handyUsers")
public class HandyUser extends User {
    private String trade;
    private double hourlyRate;
    @OneToMany(mappedBy = "handyman", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PortfolioItem> portfolioItem = new ArrayList<>();

    @OneToMany(mappedBy = "handyman", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviewsAbout = new ArrayList<>();
    // todo messages


    public HandyUser() {
    }

    public String getTrade() {
        return trade;
    }

    public void setTrade(String trade) {
        this.trade = trade;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public List<PortfolioItem> getPortfolioItem() { return portfolioItem; }

    public void setPortfolioItem(List<PortfolioItem> portfolioItem) { this.portfolioItem = portfolioItem; }

    public List<Review> getReviewsAbout() {
        return reviewsAbout;
    }

    public void setReviewsAbout(List<Review> reviewsAbout) {
        this.reviewsAbout = reviewsAbout;
    }
}
