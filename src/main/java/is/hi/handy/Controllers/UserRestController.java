package is.hi.handy.Controllers;

import is.hi.handy.Persistence.Entities.Ad;
import is.hi.handy.Persistence.Entities.HandyUser;
import is.hi.handy.Persistence.Entities.Trade;
import is.hi.handy.Persistence.Entities.User;
import is.hi.handy.Services.AdService;
import is.hi.handy.Services.PortfolioItemService;
import is.hi.handy.Services.ReviewService;
import is.hi.handy.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class UserRestController {
    private UserService userService;
    private PortfolioItemService portfolioItemService;
    private ReviewService reviewService;
    private AdService adService;

    @Autowired
    public UserRestController(UserService userService, PortfolioItemService portfolioItemService, ReviewService reviewService, AdService adService) {
        this.userService = userService;
        this.portfolioItemService = portfolioItemService;
        this.reviewService = reviewService;
        this.adService = adService;
    }

    @RequestMapping(value = "/api/handymen", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<HandyUser> showHandyUsers(Model model, @RequestParam(value = "name", required = false) String name, @RequestParam(value = "trade", required = false) Trade trade, @RequestParam(value = "orderByRating", required = false, defaultValue = "false") boolean orderByRating, @RequestParam(value = "minRate", required = false) Double minRate, @RequestParam(value = "maxRate", required = false) Double maxRate) {
        List<HandyUser> handyUsers;
        if(name == null && trade == null && !orderByRating && minRate == null && maxRate == null) {
            handyUsers = userService.findAllHandyUser();
        } else {
            handyUsers = userService.findByFilter(name, trade, minRate, maxRate, orderByRating);
        }
        /*
        model.addAttribute("nameSearch", name);
        model.addAttribute("minRateSearch", minRate);
        model.addAttribute("maxRateSearch", maxRate);
        model.addAttribute("handymen", handyUsers);
         */
        for (HandyUser handyUser : handyUsers) {
            handyUser.setAds(null);
            handyUser.setPortfolioItem(null);
            handyUser.setReviewsAbout(null);
        }
        return handyUsers;
    }

    @RequestMapping(value = "/api/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<User> login(@RequestBody User user) {
        User exists = userService.login(user);
        if(exists != null) {
            for (Ad ad : exists.getAds()) {
                ad.setUser(null);
            }
            return ResponseEntity.ok(exists);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new User());
    }
}
