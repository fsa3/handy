package is.hi.handy.Controllers;

import is.hi.handy.Persistence.Entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
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
        return "redirect:/signup";
    }

    @RequestMapping(value = "/signuphandy", method = RequestMethod.GET)
    public String signUphandyPage(Model model) {
        return "signuphandy";
    }
}