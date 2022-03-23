package is.hi.handy.Controllers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import is.hi.handy.Persistence.Entities.Ad;
import is.hi.handy.Persistence.Entities.Image;
import is.hi.handy.Persistence.Entities.Trade;
import is.hi.handy.Services.AdService;
import is.hi.handy.Services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class AdRestController {
    private AdService adService;
    private ImageService imageService;

    @Autowired
    public AdRestController(AdService adService, ImageService imageService) {
        this.adService = adService;
        this.imageService = imageService;
    }

    @RequestMapping(value = "/api/ads", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
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
}
