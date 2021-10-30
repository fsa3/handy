package is.hi.handy.Controllers;

import is.hi.handy.Services.AdService;
import org.springframework.stereotype.Controller;

@Controller
public class AdController {
private AdService adService;

public AdController(AdService adService){this.adService = adService;}



}
