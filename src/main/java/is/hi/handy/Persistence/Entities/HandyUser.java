package is.hi.handy.Persistence.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "handyUsers")
public class HandyUser extends User {
    private Trade trade;
    private double hourlyRate;
    private double averageRating;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<PortfolioItem> portfolioItem = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "handyman", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviewsAbout = new ArrayList<>();
    // todo messages


    public HandyUser() {
    }

    public Trade getTrade() {
        return trade;
    }

    public void setTrade(Trade trade) {
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

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    @Override
    public String toString() {
        return "HandyUser{" +
                "trade='" + trade + '\'' +
                ", hourlyRate=" + hourlyRate +
                ", name=" + name +
                ", email=" + email +
                '}';
    }
}
