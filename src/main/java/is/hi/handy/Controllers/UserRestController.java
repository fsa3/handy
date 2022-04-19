package is.hi.handy.Controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import is.hi.handy.Persistence.Entities.*;
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
        System.out.println(exists);
        if(exists != null) {
            for (Ad ad : exists.getAds()) {
                ad.setUser(null);
            }
            return ResponseEntity.ok(exists);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new User());
    }

    @RequestMapping(value = "/api/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User getUser(@PathVariable long id) {
        User user = userService.findUser(id);
        assert user != null;
        for (Ad a : user.getAds()) {
            a.setUser(null);
        }
        return user;
    }

    @RequestMapping(value = "/api/handyUser/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HandyUser getHandyUser(@PathVariable long id) {
        HandyUser user = userService.findOneHandyUser(id);
        assert user != null;
        for (Ad a : user.getAds()) {
            a.setUser(null);
        }
        return user;
    }
    
    @RequestMapping(value = "/api/createuser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<User> createUser(@RequestBody ObjectNode json) {
        try {
            User user = new User();
            user.setName(json.get("name").asText());
            user.setEmail(json.get("email").asText());
            user.setPassword(json.get("password").asText());

            User savedUser = userService.save(user);

            return ResponseEntity.ok().body(savedUser);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new User());
        }
    }

    @RequestMapping(value = "/api/createhandyuser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<HandyUser> createHandyUser(@RequestBody ObjectNode json) {
        try {
            HandyUser handyUser = new HandyUser();
            handyUser.setName(json.get("name").asText());
            handyUser.setEmail(json.get("email").asText());
            handyUser.setPassword(json.get("password").asText());
            handyUser.setTrade(Trade.valueOf(json.get("trade").asText()));

            HandyUser savedHandyUser = userService.save(handyUser);

            return ResponseEntity.ok().body(savedHandyUser);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new HandyUser());
        }
    }

    @RequestMapping(value = "/api/user", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<User> saveUserProfile(@RequestBody ObjectNode json) {
        try {
            User user = userService.findUser(json.get("id").asLong());
            user.setName(json.get("name").asText());
            user.setEmail(json.get("email").asText());
            user.setInfo(json.get("info").asText());

            User savedUser = userService.save(user);

            for (Ad a : savedUser.getAds()) {
                a.setUser(null);
            }

            return ResponseEntity.ok().body(savedUser);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new User());
        }
    }

    @RequestMapping(value = "/api/handyuser", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<HandyUser> saveHandyUserProfile(@RequestBody ObjectNode json) {
        try {
            HandyUser handyUser = userService.findOneHandyUser(json.get("id").asLong());
            handyUser.setName(json.get("name").asText());
            handyUser.setEmail(json.get("email").asText());
            handyUser.setInfo(json.get("info").asText());
            handyUser.setTrade(Trade.valueOf(json.get("trade").asText()));
            handyUser.setHourlyRate(json.get("hourlyRate").asDouble());

            HandyUser savedHandyUser = userService.save(handyUser);

            for (Ad a : savedHandyUser.getAds()) {
                a.setUser(null);
            }

            return ResponseEntity.ok().body(savedHandyUser);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new HandyUser());
        }
    }

    @RequestMapping(value = "/api/user/{userId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<User> deleteUser(@PathVariable long userId) {
        User user = userService.findUser(userId);
        userService.delete(user);
        return ResponseEntity.status(204).body(user);
    }
}
