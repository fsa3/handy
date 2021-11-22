package is.hi.handy.Persistence.Repositories;

import is.hi.handy.Persistence.Entities.Ad;
import is.hi.handy.Persistence.Entities.Trade;
import is.hi.handy.Persistence.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface AdRepository extends JpaRepository<Ad, Long> {

    Ad save(Ad ad);
    void delete(Ad ad);
    List<Ad> findAll();
    List<Ad> findAllByOrderByIDDesc();
    List<Ad> findAllByOrderByTimePostedDesc();
    List<Ad> findByTradeOrderByTimePostedDesc(Trade trade);
    Ad findByID(Long ID);
    List<Ad> findByTitleContainingIgnoreCase(String title);
    List<Ad> findByUser(User user);
    List<Ad> findByDescriptionContainingIgnoreCase(String description);
    List<Ad> findByLocationContainingIgnoreCase(String location);
    List<Ad> findByTimePostedGreaterThan(Timestamp timestamp);

}
