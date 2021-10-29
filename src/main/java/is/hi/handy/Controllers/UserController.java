package is.hi.handy.Controllers;

import is.hi.handy.Persistence.Entities.User;
import is.hi.handy.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signUpPage(Model model) {
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String saveUser(User user, BindingResult result, Model model) {
        System.out.println("signup POST");
        System.out.println(result);
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        userService.save(user);
        return "redirect:/";
    }

    @RequestMapping(value = "/signuphandy", method = RequestMethod.GET)
    public String signUphandyPage(Model model) {
        return "signuphandy";
    }

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