package is.hi.handy.Controllers;

import is.hi.handy.Persistence.Entities.HandyUser;
import is.hi.handy.Persistence.Entities.User;
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

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public String userViewUser(long id, Model model) {
        return null;
    }

    @RequestMapping(value = "/handymen/{id}", method = RequestMethod.GET)
    public String userViewHandy(@PathVariable("id") long id, Model model) {
        HandyUser userToView = userService.findOneHandyUser(id);
        model.addAttribute("handyman", userToView);
        return "handyUserProfile";
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
        System.out.println(user);
        userService.save(user);
        return "redirect:/";
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
        return "redirect:/users"; //todo skoða þetta redirect
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
        return "redirect:/"; //todo skoða þetta redirect
    }

    @RequestMapping(value = "/myprofile", method = RequestMethod.GET)
    public String editUser(HttpSession session, Model model) {
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        if(sessionUser != null) {
            boolean handyUserLoggedIn = (Boolean) session.getAttribute("handyUserLoggedIn");
            model.addAttribute("LoggedInUser", sessionUser);
            if (handyUserLoggedIn) {
                model.addAttribute("LoggedInUser", (HandyUser) sessionUser);
                return "editHandyUser";
            }
            return "editUser";
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "/editHandyUser", method = RequestMethod.GET)
    public String editHandyUser(HttpSession session, Model model) {
        return "editHandyUser";
    }

    @RequestMapping(value = "/users/delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("id") long id, Model model) {
        User userToDelete = userService.findUser(id);
        userService.delete(userToDelete);
        return "redirect:/users";
    }

    @RequestMapping(value = "handymen/delete/{id}", method = RequestMethod.GET)
    public String deleteHandyUser(@PathVariable("id") long id, Model model) {
        HandyUser userToDelete = userService.findOneHandyUser(id);
        userService.delete(userToDelete);
        return "redirect:/handymen";
    }

    @RequestMapping(value = "/handymen", method = RequestMethod.GET)
    public String showHandyUsers(Model model, @RequestParam(value = "name", required = false) String name, @RequestParam(value = "trade", required = false) String trade, @RequestParam(value = "orderByRating", required = false, defaultValue = "false") boolean orderByRating) {
        List<HandyUser> handyUsers = userService.findAllHandyUser();
        if (trade != null) handyUsers = userService.findHandyUserByTrade(trade);
        if (orderByRating) handyUsers = userService.orderHandyUserByRating(trade, new Double(0), new Double(0)); // value á min og max harðkóðuð tímabundið
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