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

    @RequestMapping(value = "/signuphandy", method = RequestMethod.GET)
    public String handyUserSignupForm(Model model) {
        return "signuphandy";
    }

    public String loginForm(Model model) {
        return null;
    }

    public String submitLogin(String email, String password, Model model) {
        return null;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String saveUser(User user, BindingResult result, Model model) {
        userService.save(user);
        return "redirect:/";
    }

    @RequestMapping(value = "/signuphandy", method = RequestMethod.POST)
    public String saveHandyUser(HandyUser user, BindingResult result, Model model) {
        System.out.println(user);
        userService.save(user);
        return "redirect:/";
    }

    public String editUser(User user, Model model) {
        return null;
    }

    public String editHandyUser(HandyUser user, Model model) {
        return null;
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
    public String showHandyUsers(Model model, @RequestParam(value = "trade", required = false) String trade) {
        List<HandyUser> handyUsers = userService.findAllHandyUser();
        if (trade != null) handyUsers = userService.findHandyUserByTrade(trade);
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