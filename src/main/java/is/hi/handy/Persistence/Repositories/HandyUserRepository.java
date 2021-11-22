package is.hi.handy.Persistence.Repositories;

import is.hi.handy.Persistence.Entities.Ad;
import is.hi.handy.Persistence.Entities.HandyUser;
import is.hi.handy.Persistence.Entities.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HandyUserRepository extends JpaRepository<HandyUser, Long> {
    HandyUser save(HandyUser user);
    void delete(HandyUser user);
    List<HandyUser> findAll();
    HandyUser findByID(Long id);
    HandyUser findByEmail(String email);
    List<HandyUser> findByName(String name);
    List<HandyUser> findByNameLike(String name);
    List<HandyUser> findByTrade(Trade trade);
    List<HandyUser> findByHourlyRateBetween(double minRate, double maxRate);
    List<HandyUser> findAllByOrderByAverageRatingDesc();
    List<HandyUser> findByTradeOrderByAverageRatingDesc(Trade trade);
    List<HandyUser> findByHourlyRateBetweenOrderByAverageRatingDesc(double minRate, double maxRate);
    List<HandyUser> findByTradeAndHourlyRateBetweenOrderByAverageRatingDesc(Trade trade, double minRate, double maxRate);
}
