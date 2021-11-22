package is.hi.handy.Services;

import is.hi.handy.Persistence.Entities.Ad;
import is.hi.handy.Persistence.Entities.Trade;
import is.hi.handy.Persistence.Entities.User;

import java.sql.Timestamp;
import java.util.List;

public interface AdService {
    Ad save(Ad ad);
    void delete(Ad ad);
    List<Ad> findAll();
    List<Ad> findAllOrderByIdDesc();
    List<Ad> findAllOrderByTimePostedDesc();
    List<Ad> findByTrade(Trade trade);
    Ad findOne(Long id);
    List<Ad> findByTitle(String title);
    List<Ad> findByUser(User user);
    List<Ad> findByDescription(String description);
    List<Ad> findByLocation(String location);
    List<Ad> findByTimePostedGreaterThan(Timestamp timestamp);

}
