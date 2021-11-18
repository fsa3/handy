package is.hi.handy.Controllers;

import is.hi.handy.Persistence.Entities.HandyUser;
import is.hi.handy.Persistence.Entities.Review;
import is.hi.handy.Persistence.Entities.User;
import is.hi.handy.Services.ReviewService;
import is.hi.handy.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class ReviewController {
    ReviewService reviewService;
    UserService userService;

    @Autowired
    ReviewController (ReviewService reviewService, UserService userService) {
        this.reviewService = reviewService;
        this.userService = userService;
    }

    @RequestMapping(value = "/createReview/{handyUserId}", method = RequestMethod.GET)
    public String createReview(Model model, HttpSession session, @PathVariable("handyUserId") long handyUserId) {
        User loggedInUser = (User) session.getAttribute("LoggedInUser");
        if(loggedInUser != null) {
            model.addAttribute("LoggedInUser", loggedInUser);
            model.addAttribute("reviewAbout", userService.findOneHandyUser(handyUserId));
            return "createReview";
        }
        return "login";
    }

    @RequestMapping(value = "createReview/{handyUserId}", method = RequestMethod.POST)
    public String save(Model model, Review review, @PathVariable("handyUserId") long handyUserId, HttpSession session) {
        HandyUser reviewAbout = userService.findOneHandyUser(handyUserId);
        User loggedInUser = (User) session.getAttribute("LoggedInUser");
        review.setAuthor(loggedInUser);
        review.setHandyman(reviewAbout);
        reviewService.save(review);
        return "redirect:/handymen/" + handyUserId;
    }
}
