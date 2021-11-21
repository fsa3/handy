package is.hi.handy.Controllers;

import is.hi.handy.Persistence.Entities.*;
import is.hi.handy.Services.ImageService;
import is.hi.handy.Services.PortfolioItemService;
import is.hi.handy.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class PortfolioItemController {
    PortfolioItemService portfolioItemService;
    UserService userService;
    ImageService imageService;

    @Autowired
    PortfolioItemController(PortfolioItemService portfolioItemService, UserService userService, ImageService imageService){
        this.portfolioItemService = portfolioItemService;
        this.userService = userService;
        this.imageService = imageService;
    }

    @RequestMapping(value = "/createPortfolioItem", method = RequestMethod.GET)
    public String creatPortfolioItem(Model model, HttpSession session){
        User loggedInUser = (User) session.getAttribute("LoggedInUser");
        if(loggedInUser != null) {
            model.addAttribute("LoggedInUser", loggedInUser);
            return "createPortfolioItem";
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "/createPortfolioItem", method = RequestMethod.POST)
    public String save(Model model, PortfolioItem portfolioItem, @RequestParam("file") MultipartFile file, HttpSession session) throws IOException {
        User loggedInUser = (User) session.getAttribute("LoggedInUser");
        //byte[] image = file.getBytes();
        Image image = new Image(file.getBytes());
        portfolioItem.setImage(image);
        portfolioItem.setUser((HandyUser) loggedInUser);
        imageService.save(image);
        portfolioItemService.save(portfolioItem);
        return "redirect:/myprofile";
    }

}
