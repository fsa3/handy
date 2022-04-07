package is.hi.handy.Controllers;

import is.hi.handy.Persistence.Entities.Review;
import is.hi.handy.Persistence.Entities.User;
import is.hi.handy.Services.ReviewService;
import is.hi.handy.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value ="/api/reviews", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Review> getReviews(@RequestParam(value = "user", required = false) User user)  {
        List<Review> Reviews;
        if(user != null){ Reviews = getReviews(user);}
        else
            Reviews = null;

        return Reviews;
    }


}
