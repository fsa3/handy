package is.hi.handy.Controllers;

import is.hi.handy.Persistence.Entities.Ad;
import is.hi.handy.Persistence.Entities.Image;
import is.hi.handy.Persistence.Entities.Trade;
import is.hi.handy.Persistence.Entities.User;
import is.hi.handy.Services.AdService;
import is.hi.handy.Services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

/* Þetta er í vinnslu*/

@Controller
public class AdController {
    private AdService adService;
    private ImageService imageService;

    @Autowired
    public AdController(AdService adService, ImageService imageService) {
        this.adService = adService;
        this.imageService = imageService;
    }

    @RequestMapping(value = "/ads", method = RequestMethod.GET)
    public String adForm(Model model, HttpSession session, @RequestParam(value = "trade", required = false) Trade trade, @RequestParam(value = "search", required = false) String searchString) {
        model.addAttribute("LoggedInUser", session.getAttribute("LoggedInUser"));
        List<Ad> advertisements;
        if (trade != null) advertisements = adService.findByTrade(trade);
        else if (searchString != null) {
            model.addAttribute("searchString", searchString);
            advertisements = adService.findAdBySearch(searchString);
        }
        else advertisements = adService.findAllOrderByTimePostedDesc();
        model.addAttribute("ads", advertisements);
        return "ads";
    }

    @RequestMapping(value = "/ads/search", method = RequestMethod.POST)
    public String searchForAd(String searchQuery) {
        return "redirect:/ads?search=" + URLEncoder.encode(searchQuery, StandardCharsets.UTF_8);
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
    public String saveAd(Ad ad, BindingResult result, Model model, HttpSession session, @RequestParam("file") MultipartFile file) throws IOException {
        User loggedInUser = (User) session.getAttribute("LoggedInUser");
        if(!result.hasErrors()){
            Image image = new Image(file.getBytes());
            image = imageService.save(image);
            ad.setImage(image);
            ad.setUser(loggedInUser);
            adService.save(ad);
            return "redirect:/ads";
        }
        return "createad";
    }

    @RequestMapping(value = "/deleteAd{id}", method = RequestMethod.GET)
    public String deleteAd(@PathVariable("id") long id, HttpSession session, Model model) {
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        model.addAttribute("LoggedInUser", sessionUser);
        Ad adToDelete = adService.findOne(id);
        if (sessionUser.getID() != adToDelete.getUser().getID()) {
            return "redirect:/";
        }
        adService.delete(adToDelete);
        return "redirect:/myprofile";
    }
}
