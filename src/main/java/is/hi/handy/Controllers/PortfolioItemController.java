package is.hi.handy.Controllers;

import is.hi.handy.Persistence.Entities.HandyUser;
import is.hi.handy.Persistence.Entities.PortfolioItem;
import is.hi.handy.Persistence.Entities.Review;
import is.hi.handy.Persistence.Entities.User;
import is.hi.handy.Services.PortfolioItemService;
import is.hi.handy.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class PortfolioItemController {
    PortfolioItemService portfolioItemService;
    UserService userService;

    @Autowired
    PortfolioItemController(PortfolioItemService portfolioItemService, UserService userService){
        this.portfolioItemService = portfolioItemService;
        this.userService = userService;
    }

    @RequestMapping(value = "/createPortfolioItem/{handyUserId}", method = RequestMethod.GET)
    public String creatPortfolioItem(Model model, HttpSession session, @PathVariable("handyUserId") long handyUserID){
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if(loggedInUser != null) {
            model.addAttribute("LoggedInUser", loggedInUser);
            return "createPortfolioItem";
        }
        return "login";
    }
    @RequestMapping(value = "createPortfolioItem/{handyUserId}", method = RequestMethod.POST)
    public String save(Model model, PortfolioItem portfolioItem, @PathVariable("handyUserId") long handyUserId, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("LoggedInUser");
        portfolioItem.setUser((HandyUser) loggedInUser);
        portfolioItemService.save(portfolioItem);
        return "redirect:/editHandyUser/" + handyUserId;
    }

}
