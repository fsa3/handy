package is.hi.handy.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signUpPage(Model model) {
        return "signup";
    }

    @RequestMapping(value = "/signuphandy", method = RequestMethod.GET)
    public String signUphandyPage(Model model) {
        return "signuphandy";
    }
}