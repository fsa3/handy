package is.hi.handy.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String homePage(Model model) {
        return "home";
    }

    @RequestMapping(value = "/information", method = RequestMethod.GET)
    public String infoPage(){
        return "information";
    }

}
