package is.hi.handy.Controllers;

import is.hi.handy.Persistence.Entities.HandyUser;
import is.hi.handy.Persistence.Entities.Review;
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


}
