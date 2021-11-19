package is.hi.handy.Controllers;

import is.hi.handy.Persistence.Entities.Ad;
import is.hi.handy.Persistence.Entities.HandyUser;
import is.hi.handy.Persistence.Entities.User;
import is.hi.handy.Services.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.servlet.http.HttpSession;
import java.nio.file.FileStore;
import java.util.List;

/* Þetta er í vinnslu*/

@Controller
public class AdController {
private AdService adService;

@Autowired
public AdController(AdService adService){this.adService = adService;}

    @RequestMapping("/ads")
     public String adForm(Model model, HttpSession session) {
        model.addAttribute("LoggedInUser", session.getAttribute("LoggedInUser"));
        List<Ad> advertisements = adService.findAllOrderByTimePostedDesc();
        model.addAttribute("ads", advertisements);

        return "ads";
    }

    @RequestMapping(value = "/ads/{id}", method = RequestMethod.GET)
    public String adInfoGet(@PathVariable("id") long id, Model model, HttpSession session){
        model.addAttribute("LoggedInUser", session.getAttribute("LoggedInUser"));

        Ad detailedAd = adService.findOne(id);
        model.addAttribute("ad", detailedAd);
        return "adInfo";
    }



    @RequestMapping(value = "/createad", method = RequestMethod.GET)
    public String adFormGet(Model model, HttpSession session){
        User loggedInUser = (User) session.getAttribute("LoggedInUser");
        model.addAttribute("LoggedInUser", loggedInUser);
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

    @RequestMapping(value = "/ads/delete/{id}", method = RequestMethod.GET)
    public String deleteAd(@PathVariable("id") long id, HttpSession session, Model model) {
        Ad adToDelete = adService.findOne(id);
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        System.out.println(sessionUser);
        System.out.println(adToDelete.getUser());
        if(sessionUser == adToDelete.getUser()){ //todo er ekki að virka eins skildi
            adService.delete(adToDelete);
        }
        return "redirect:/ads";
    }
}
