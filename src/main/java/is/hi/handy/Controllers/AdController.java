package is.hi.handy.Controllers;

import is.hi.handy.Persistence.Entities.Ad;
import is.hi.handy.Persistence.Entities.User;
import is.hi.handy.Services.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.servlet.http.HttpSession;
import java.util.List;

/* Þetta er í vinnslu*/

@Controller
public class AdController {
private AdService adService;

@Autowired
public AdController(AdService adService){this.adService = adService;}

    @RequestMapping("/ads")
     public String adForm(Model model) {

        List<Ad> advertisements = adService.findAll();
        model.addAttribute("ads", advertisements);

        return "ads";
    }

    @RequestMapping(value = "/createad", method = RequestMethod.GET)
    public String adFormGet(Model model, HttpSession session){
        User loggedInUser = (User) session.getAttribute("LoggedInUser");
        if(loggedInUser != null) {
            return "createAd";
        }
        return "login";
    }

    @RequestMapping(value = "/createad", method = RequestMethod.POST)
    public String saveAd(Ad ad, BindingResult result, Model model, HttpSession session){
        System.out.println(ad);
        if(result.hasErrors()){
            return "createad";
        }
        User loggedInUser = (User) session.getAttribute("LoggedInUser");
        ad.setUser(loggedInUser);
        adService.save(ad);
        return "redirect:/ads";
    }
}
