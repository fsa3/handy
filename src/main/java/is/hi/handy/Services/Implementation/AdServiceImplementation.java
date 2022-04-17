package is.hi.handy.Services.Implementation;

import is.hi.handy.Persistence.Entities.Ad;
import is.hi.handy.Persistence.Entities.Image;
import is.hi.handy.Persistence.Entities.Trade;
import is.hi.handy.Persistence.Entities.User;
import is.hi.handy.Persistence.Repositories.AdRepository;
import is.hi.handy.Services.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.*;

@Service
public class AdServiceImplementation implements AdService {
    AdRepository adRepository;

    @Autowired
    public AdServiceImplementation(AdRepository repository) {
        this.adRepository = repository;
    }

    @Override
    public Ad save(Ad ad) {
        return adRepository.save(ad);
    }

    @Override
    public void delete(Ad ad) {
        adRepository.delete(ad);
    }

    @Override
    public List<Ad> findAll() {
        return setAdsImages(adRepository.findAll());

    }

    @Override
    public List<Ad> findAllOrderByIdDesc() {
        return setAdsImages(adRepository.findAllByOrderByIDDesc());
    }

    @Override
    public List<Ad> findAllOrderByTimePostedDesc() {
        return setAdsImages(adRepository.findAllByOrderByTimePostedDesc());
    }

    @Override
    public List<Ad> findByTrade(Trade trade) {
        return setAdsImages(adRepository.findByTradeOrderByTimePostedDesc(trade));
    }

    @Override
    public Ad findOne(Long id) {
        return setAdImage(adRepository.findByID(id));
    }

    @Override
    public List<Ad> findByTitle(String title) {
        return setAdsImages(adRepository.findByTitleContainingIgnoreCase(title));
    }

    @Override
    public List<Ad> findByUser(User user) {
        return setAdsImages(adRepository.findByUser(user));
    }

    @Override
    public List<Ad> findByDescription(String description) {
        return setAdsImages(adRepository.findByDescriptionContainingIgnoreCase(description));
    }

    @Override
    public List<Ad> findByLocation(String location) {
        return null;
    }

    @Override
    public List<Ad> findByTimePostedGreaterThan(Timestamp timestamp) {
        return setAdsImages(adRepository.findByTimePostedGreaterThan(timestamp));
    }

    @Override
    public List<Ad> findAdBySearch(String search) {
        Set<Ad> adsSet = new HashSet<>();
        adsSet.addAll(adRepository.findByTitleContainingIgnoreCase(search));
        adsSet.addAll(adRepository.findByDescriptionContainingIgnoreCase(search));
        adsSet.addAll(adRepository.findByLocationContainingIgnoreCase(search));
        List<Ad> ads = new ArrayList<Ad>(adsSet);
        Collections.sort(ads);
        return setAdsImages(ads);
    }

    private Ad setAdImage(Ad ad) {
        if(ad.getImage() != null) {
            byte[] encode = java.util.Base64.getEncoder().encode(ad.getImage().getImage());
            try {
                ad.setStringImage(new String(encode, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return ad;
    }

    private List<Ad> setAdsImages(List<Ad> ads) {
        for(Ad ad: ads) {
            if(ad.getImage() == null) continue;
            byte[] encode = java.util.Base64.getEncoder().encode(ad.getImage().getImage());
            try {
                ad.setStringImage(new String(encode, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return ads;
    }
}
