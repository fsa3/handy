package is.hi.handy.Controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import is.hi.handy.Persistence.Entities.HandyUser;
import is.hi.handy.Persistence.Entities.Image;
import is.hi.handy.Persistence.Entities.PortfolioItem;
import is.hi.handy.Persistence.Entities.User;
import is.hi.handy.Services.ImageService;
import is.hi.handy.Services.PortfolioItemService;
import is.hi.handy.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


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

    @RequestMapping(value = "/api/createPortfolioItem", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<PortfolioItem> createPortfolioItem(@RequestBody ObjectNode json) {
        try {
            HandyUser handyUser = userService.findOneHandyUser(json.get("user").asLong());
            ArrayList<Byte> imageBytes = new ArrayList<>();
            for(JsonNode b : json.get("imageBYtes")) {
                imageBytes.add((byte) b.asInt());
            }
            Image image = new Image(imageBytes);

            PortfolioItem item = new PortfolioItem();
            item.setTitle(json.get("title").asText());
            item.setLocation(json.get("location").asText());
            item.setDescription(json.get("description").asText());
            item.setUser(handyUser);
            item.setImage(image);

            imageService.save(image);
            PortfolioItem savedItem = portfolioItemService.save(item);
            savedItem.getUser().setPortfolioItem(null);
            savedItem.setImage(null);

            return ResponseEntity.ok().body(savedItem);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new PortfolioItem());
        }
    }

    @RequestMapping(value = "/api/portfolioItem/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<PortfolioItem> deletePortfolioItem(@PathVariable long id) {
        PortfolioItem item = portfolioItemService.findByID(id);
        portfolioItemService.delete(item);
        return ResponseEntity.status(204).body(item);
    }


}

