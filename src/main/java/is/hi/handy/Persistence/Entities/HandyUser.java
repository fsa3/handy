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
    private String trade; // verður mögulega eitthvað annað en String
    private double hourlyRate;
    private double averageRating; // á eftir að skrifa virkni sem reiknar averageRating út frá reviews
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
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
