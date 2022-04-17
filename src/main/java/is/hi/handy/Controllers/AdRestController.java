package is.hi.handy.Controllers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import is.hi.handy.Persistence.Entities.Ad;
import is.hi.handy.Persistence.Entities.Image;
import is.hi.handy.Persistence.Entities.Trade;
import is.hi.handy.Persistence.Entities.User;
import is.hi.handy.Services.AdService;
import is.hi.handy.Services.ImageService;
import is.hi.handy.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class AdRestController {
    private AdService adService;
    private UserService userService;
    private ImageService imageService;

    @Autowired
    public AdRestController(AdService adService, ImageService imageService, UserService userService) {
        this.adService = adService;
        this.imageService = imageService;
        this.userService = userService;
    }

    @RequestMapping(value = "/api/ads", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Ad> getAds(@RequestParam(value = "trade", required = false) Trade trade, @RequestParam(value = "search", required = false) String searchString) throws InterruptedException {
        List<Ad> advertisements;
        if (trade != null) advertisements = adService.findByTrade(trade);
        else if (searchString !=  null) {
            advertisements = adService.findAdBySearch(searchString);
        }
        else advertisements = adService.findAllOrderByTimePostedDesc();
        for(Ad ad : advertisements) {
            ad.getUser().setAds(null);
            ad.setImage(null);
        }
        return advertisements;
    }

    @RequestMapping(value = "/api/ads/user/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Ad> getAdsByUser(@PathVariable long userId) throws InterruptedException {
        User user = userService.findUser(userId);
        List<Ad> advertisements = adService.findByUser(user);
        for(Ad ad : advertisements) {
            ad.getUser().setAds(null);
            ad.setImage(null);
        }
        return advertisements;
    }

    @RequestMapping(value = "/api/createad", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Ad> createAd(@RequestBody ObjectNode json) {
        try {
            User user = userService.findUser(json.get("user").asLong());
            ArrayList<Byte> imageBytes = new ArrayList<>();
            for (JsonNode b : json.get("imageBytes")) {
                imageBytes.add((byte) b.asInt());
            }
            Image image = new Image(imageBytes);

            Ad ad = new Ad();
            ad.setTitle(json.get("title").asText());
            ad.setDescription(json.get("description").asText());
            ad.setLocation(json.get("location").asText());
            ad.setTrade(Trade.valueOf(json.get("trade").asText()));
            ad.setUser(user);
            ad.setImage(image);

            imageService.save(image);
            Ad savedAd = adService.save(ad);
            savedAd.getUser().setAds(null);
            savedAd.setImage(null);

            return ResponseEntity.ok().body(savedAd);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new Ad());
        }
    }

    @RequestMapping(value = "/api/ads/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Ad> deleteAd(@PathVariable long id) {
        Ad ad = adService.findOne(id);
        adService.delete(ad);
        return ResponseEntity.status(204).body(ad);
    }
}
