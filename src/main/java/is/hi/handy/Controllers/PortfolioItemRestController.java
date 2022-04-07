package is.hi.handy.Controllers;

import is.hi.handy.Persistence.Entities.PortfolioItem;
import is.hi.handy.Persistence.Entities.User;
import is.hi.handy.Services.ImageService;
import is.hi.handy.Services.PortfolioItemService;
import is.hi.handy.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PortfolioItemRestController {
    PortfolioItemService portfolioItemService;
    UserService userService;
    ImageService imageService;

    @Autowired
    public PortfolioItemRestController(PortfolioItemService portfolioItemService, UserService userService, ImageService imageService){
        this.portfolioItemService = portfolioItemService;
        this.userService = userService;
        this.imageService = imageService;
    }

        @RequestMapping(value ="/api/portfolio_item", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
        public PortfolioItem getPortfolioItem(@RequestParam(value = "user", required = false) User user)  {
            PortfolioItem portfolioItem;
            if(user != null){ portfolioItem = getPortfolioItem(user);}
            else
                portfolioItem = null;

            return portfolioItem;
        }


    }

