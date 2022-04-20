package is.hi.handy.Controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import is.hi.handy.Persistence.Entities.HandyUser;
import is.hi.handy.Persistence.Entities.Review;
import is.hi.handy.Persistence.Entities.User;
import is.hi.handy.Services.ReviewService;
import is.hi.handy.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReviewRestController {
    ReviewService reviewService;
    UserService userService;

    @Autowired
    public ReviewRestController(ReviewService reviewService, UserService userService){
        this.reviewService = reviewService;
        this.userService = userService;
    }

    @RequestMapping(value ="/api/reviews/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Review> getReviews(@PathVariable long userId)  {
        HandyUser handy = userService.findOneHandyUser(userId);
        List<Review> reviews = reviewService.findByHandyman(handy);

        for (Review r : reviews) {
            r.getHandyman().setAds(null);
            r.getAuthor().setAds(null);
        }

        return reviews;
    }

    @RequestMapping(value ="/api/reviews-written/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Review> getReviewsWrittenByUser(@PathVariable long userId)  {
        User user = userService.findUser(userId);
        List<Review> reviews = reviewService.findByAuthor(user);

        for (Review r : reviews) {
            r.getHandyman().setAds(null);
            r.getAuthor().setAds(null);
        }

        return reviews;
    }

    @RequestMapping(value ="/api/reviews", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Review postReview(@RequestBody ObjectNode json)  {
        Review review = new Review();

        User author = userService.findUser(json.get("author").asLong());
        HandyUser handyman = userService.findOneHandyUser(json.get("handyman").asLong());
        String text = json.get("text").asText();
        int rating = json.get("rating").asInt();

        review.setAuthor(author);
        review.setHandyman(handyman);
        review.setText(text);
        review.setRating(rating);

        Review savedReview = reviewService.save(review);

        savedReview.getAuthor().setAds(null);
        savedReview.getHandyman().setAds(null);

        return savedReview;
    }
}
