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
    List<HandyUser> findAllByOrderByAverageRatingDesc();

    List<HandyUser> findByNameContainingIgnoreCase(String name);
    List<HandyUser> findByNameContainingIgnoreCaseOrderByAverageRatingDesc(String name);

    List<HandyUser> findByTrade(Trade trade);
    List<HandyUser> findByTradeOrderByAverageRatingDesc(Trade trade);

    List<HandyUser> findByHourlyRateGreaterThan(double minRate);
    List<HandyUser> findByHourlyRateGreaterThanOrderByAverageRatingDesc(double minRate);

    List<HandyUser> findByHourlyRateLessThan(double maxRate);
    List<HandyUser> findByHourlyRateLessThanOrderByAverageRatingDesc(double maxRate);

    List<HandyUser> findByNameContainingIgnoreCaseAndTrade(String name, Trade trade);
    List<HandyUser> findByNameContainingIgnoreCaseAndTradeOrderByAverageRatingDesc(String name, Trade trade);

    List<HandyUser> findByNameContainingIgnoreCaseAndHourlyRateGreaterThan(String name, double minRate);
    List<HandyUser> findByNameContainingIgnoreCaseAndHourlyRateGreaterThanOrderByAverageRatingDesc(String name, double minRate);

    List<HandyUser> findByNameContainingIgnoreCaseAndHourlyRateLessThan(String name, double maxRate);
    List<HandyUser> findByNameContainingIgnoreCaseAndHourlyRateLessThanOrderByAverageRatingDesc(String name, double maxRate);

    List<HandyUser> findByTradeAndHourlyRateGreaterThan(Trade trade, double minRate);
    List<HandyUser> findByTradeAndHourlyRateGreaterThanOrderByAverageRatingDesc(Trade trade, double minRate);

    List<HandyUser> findByTradeAndHourlyRateLessThan(Trade trade, double maxRate);
    List<HandyUser> findByTradeAndHourlyRateLessThanOrderByAverageRatingDesc(Trade trade, double maxRate);

    List<HandyUser> findByHourlyRateBetween(double minRate, double maxRate);
    List<HandyUser> findByHourlyRateBetweenOrderByAverageRatingDesc(double minRate, double maxRate);

    List<HandyUser> findByNameContainingIgnoreCaseAndTradeAndHourlyRateGreaterThan(String name, Trade trade, double minRate);
    List<HandyUser> findByNameContainingIgnoreCaseAndTradeAndHourlyRateGreaterThanOrderByAverageRatingDesc(String name, Trade trade, double minRate);

    List<HandyUser> findByNameContainingIgnoreCaseAndTradeAndHourlyRateLessThan(String name, Trade trade, double maxRate);
    List<HandyUser> findByNameContainingIgnoreCaseAndTradeAndHourlyRateLessThanOrderByAverageRatingDesc(String name, Trade trade, double maxRate);

    List<HandyUser> findByNameContainingIgnoreCaseAndHourlyRateBetween(String name, double minRate, double maxRate);
    List<HandyUser> findByNameContainingIgnoreCaseAndHourlyRateBetweenOrderByAverageRatingDesc(String name, double minRate, double maxRate);

    List<HandyUser> findByTradeAndHourlyRateBetween(Trade trade, double minRate, double maxRate);
    List<HandyUser> findByTradeAndHourlyRateBetweenOrderByAverageRatingDesc(Trade trade, double minRate, double maxRate);

    List<HandyUser> findByNameContainingIgnoreCaseAndTradeAndHourlyRateBetween(String name, Trade trade, double minRate, double maxRate);
    List<HandyUser> findByNameContainingIgnoreCaseAndTradeAndHourlyRateBetweenOrderByAverageRatingDesc(String name, Trade trade, double minRate, double maxRate);
}
