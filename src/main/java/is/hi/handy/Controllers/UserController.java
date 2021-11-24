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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {
    private UserService userService;
    private PortfolioItemService portfolioItemService;
    private ReviewService reviewService;
    private AdService adService;

    @Autowired
    public UserController(UserService userService, PortfolioItemService portfolioItemService, ReviewService reviewService, AdService adService) {
        this.userService = userService;
        this.portfolioItemService = portfolioItemService;
        this.reviewService = reviewService;
        this.adService = adService;
    }

    @RequestMapping(value = "/handymen/{id}", method = RequestMethod.GET)
    public String userViewHandy(@PathVariable("id") long id, Model model, HttpSession session) {
        model.addAttribute("LoggedInUser", session.getAttribute("LoggedInUser"));
        HandyUser userToView = userService.findOneHandyUser(id);
        model.addAttribute("handyman", userToView);
        model.addAttribute("handymanReviews", userToView.getReviewsAbout());
        model.addAttribute("handymanPortfolioItems", portfolioItemService.findByHandyUser(userToView));
        return "handyUserProfile";
    }


    @RequestMapping(value = "/userProfile/{handyUserId}", method = RequestMethod.GET)
    public String userView(@PathVariable("handyUserId") long handyUserId, Model model, HttpSession session) {
        model.addAttribute("LoggedInUser", session.getAttribute("LoggedInUser"));
        User userToView = userService.findUser(handyUserId);
        List<Ad> adsToView = adService.findByUser(userToView);
        model.addAttribute("user", userToView);
        model.addAttribute("userAds", adsToView);
        //System.out.println(adsToView);

        return "userProfile";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signUpForm(Model model) {
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signUp(User user, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "redirect:/signup";
        }
        User exists = userService.findByEmail(user.getEmail());
        if(exists == null) {
            userService.save(user);
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "/signuphandy", method = RequestMethod.GET)
    public String handyUserSignupForm(Model model) {
        return "signuphandy";
    }

    @RequestMapping(value = "/signuphandy", method = RequestMethod.POST)
    public String signUpHandy(HandyUser user, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "redirect:/signup";
        }
        User exists = userService.findByEmail(user.getEmail());
        if(exists == null) {
            userService.save(user);
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm(Model model) {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String submitLogin(User user, BindingResult result, Model model, HttpSession session) {
        if(result.hasErrors()) {
            return "login";
        }
        User exists = userService.login(user);
        if(exists != null) {
            if (exists instanceof HandyUser) {
                session.setAttribute("handyUserLoggedIn", true);
            }
            else session.setAttribute("handyUserLoggedIn", false);
            session.setAttribute("LoggedInUser", exists);
            model.addAttribute("LoggedInUser", exists);
            return "redirect:/myprofile";
        }
        model.addAttribute("loginError", true);
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model, HttpSession session) {
        session.setAttribute("LoggedInUser", null);
        model.addAttribute("LoggedInUser", null);
        return "redirect:/";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteUser(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("LoggedInUser");
        userService.delete(loggedInUser);
        session.setAttribute("LoggedInUser", null);
        model.addAttribute("LoggedInUser", null);
        return "redirect:/";
    }

    @RequestMapping(value = "saveuser", method = RequestMethod.POST)
    public String saveUser(User user, BindingResult result, Model model, HttpSession session) {
        if(result.hasErrors()) {
            return "editUser";
        }
        User loggedInUser = (User) session.getAttribute("LoggedInUser");
        assert loggedInUser != null;
        loggedInUser.setName(user.getName());
        loggedInUser.setEmail(user.getEmail());
        loggedInUser.setInfo(user.getInfo());
        userService.save(loggedInUser);
        session.setAttribute("LoggedInUser", loggedInUser);
        model.addAttribute("LoggedInUser", loggedInUser);
        return "redirect:/myprofile/?saved=true";
    }

    @RequestMapping(value = "savehandyuser", method = RequestMethod.POST)
    public String saveHandyUser(HandyUser user, BindingResult result, Model model, HttpSession session) {
        if(result.hasErrors()) {
            return "editUser";
        }
        HandyUser loggedInUser = (HandyUser) session.getAttribute("LoggedInUser");
        assert loggedInUser != null;
        assert (boolean) session.getAttribute("handyUserLoggedIn");
        loggedInUser.setName(user.getName());
        loggedInUser.setEmail(user.getEmail());
        loggedInUser.setTrade(user.getTrade());
        loggedInUser.setHourlyRate(user.getHourlyRate());
        loggedInUser.setInfo(user.getInfo());
        userService.save(loggedInUser);
        session.setAttribute("LoggedInUser", loggedInUser);
        model.addAttribute("LoggedInUser", loggedInUser);
        return "redirect:/myprofile/?saved=true";
    }

    @RequestMapping(value = "/myprofile", method = RequestMethod.GET)
    public String editUser(HttpSession session, Model model, @RequestParam(value = "saved", required = false, defaultValue = "false") Boolean saved) {
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        if(sessionUser != null) {
            model.addAttribute("saved", (boolean) saved);
            boolean handyUserLoggedIn = (Boolean) session.getAttribute("handyUserLoggedIn");
            model.addAttribute("LoggedInUser", sessionUser);
            model.addAttribute("reviewsWritten", reviewService.findByAuthor(sessionUser));
            if (handyUserLoggedIn) {
                model.addAttribute("LoggedInUser", (HandyUser) sessionUser);
                model.addAttribute("myPortfolioItems", portfolioItemService.findByHandyUser((HandyUser) sessionUser));
                return "editHandyUser";
            }
            return "editUser";
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "/handymen", method = RequestMethod.GET)
    public String showHandyUsers(Model model, HttpSession session, @RequestParam(value = "name", required = false) String name, @RequestParam(value = "trade", required = false) Trade trade, @RequestParam(value = "orderByRating", required = false, defaultValue = "false") boolean orderByRating, @RequestParam(value = "minRate", required = false) Double minRate, @RequestParam(value = "maxRate", required = false) Double maxRate) {
        model.addAttribute("LoggedInUser", session.getAttribute("LoggedInUser"));
        List<HandyUser> handyUsers;
        if(name == null && trade == null && !orderByRating && minRate == null && maxRate == null) {
            handyUsers = userService.findAllHandyUser();
        } else {
            handyUsers = userService.findByFilter(name, trade, minRate, maxRate, orderByRating);
        }
        model.addAttribute("nameSearch", name);
        model.addAttribute("handymen", handyUsers);
        return "handymen";
    }

    // ekki í klasariti, aðeins fyrir debugging
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String showAllUsers(Model model) {
        List<User> allUsers = userService.findAllUsers();
        model.addAttribute("users", allUsers);
        if (allUsers.isEmpty()) System.out.println("User tafla tóm");
        for (User u : allUsers) {
            System.out.println("email" + u.getEmail());
            System.out.println("id" + u.getID());
        }
        return "users";
    }
}
