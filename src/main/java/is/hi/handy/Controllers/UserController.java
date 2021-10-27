package is.hi.handy.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    @RequestMapping("/signup")
    public String signUpPage(Model model) {
        return "signup";
    }


}
