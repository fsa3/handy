package is.hi.handy.Controllers;

import is.hi.handy.Persistence.Entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String homePage(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("LoggedInUser");
        model.addAttribute("LoggedInUser", loggedInUser);
        return "home";
    }

    @RequestMapping(value = "/information", method = RequestMethod.GET)
    public String infoPage(){
        return "information";
    }

}
