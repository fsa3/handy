package is.hi.handy.Services.Implementation;

import is.hi.handy.Persistence.Entities.HandyUser;
import is.hi.handy.Persistence.Entities.Trade;
import is.hi.handy.Persistence.Entities.User;
import is.hi.handy.Persistence.Repositories.HandyUserRepository;
import is.hi.handy.Persistence.Repositories.UserRepository;
import is.hi.handy.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImplementation implements UserService {
    private UserRepository userRepository;
    private HandyUserRepository handyUserRepository;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository, HandyUserRepository handyUserRepository) {
        this.userRepository = userRepository;
        this.handyUserRepository = handyUserRepository;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    // todo skoða að sameina aðferðir
    @Override
    public HandyUser save(HandyUser user) {
        return handyUserRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    // todo skoða að sameina aðferðir
    @Override
    public void delete(HandyUser user) {
        handyUserRepository.delete(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUser(Long id) {
        return userRepository.findByID(id);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public List<User> findByNameLike(String name) {
        return userRepository.findByNameLike(name);
    }

    @Override
    public List<HandyUser> findAllHandyUser() {
        return handyUserRepository.findAll();
    }

    @Override
    public HandyUser findOneHandyUser(Long id) {
        return handyUserRepository.findByID(id);
    }

    @Override
    public HandyUser findHandyUserByEmail(String email) {
        return handyUserRepository.findByEmail(email);
    }

    @Override
    public List<HandyUser> findHandyUserByName(String name) {
        return handyUserRepository.findByName(name);
    }

    @Override
    public List<HandyUser> findHandyUserByNameLike(String name) {
        return handyUserRepository.findByNameLike(name);
    }

    @Override
    public List<HandyUser> findHandyUserByTrade(Trade trade) {
        return handyUserRepository.findByTrade(trade);
    }

    @Override
    public List<HandyUser> findHandyUserByRate(double minRate, double maxRate) {
        return handyUserRepository.findByHourlyRateBetween(minRate, maxRate);
    }

    @Override
    public List<HandyUser> orderHandyUserByRating(Trade trade, Double minRate, Double maxRate) {
        List<HandyUser> handyUsers;
        if (trade != null && minRate != null && maxRate != null) {
            handyUsers = handyUserRepository.findByTradeAndHourlyRateBetweenOrderByAverageRatingDesc(trade, minRate, maxRate);
        }
        else if (trade != null && minRate == null && maxRate == null) {
            handyUsers = handyUserRepository.findByTradeOrderByAverageRatingDesc(trade);
        }
        else if (trade == null && minRate != null && maxRate != null) {
            handyUsers = handyUserRepository.findByHourlyRateBetweenOrderByAverageRatingDesc(minRate, maxRate);
        }
        else {
            handyUsers = handyUserRepository.findAllByOrderByAverageRatingDesc();
        }
        return handyUsers;
    }

    // á örgl ekki að vera svona, gerði bara nkl eins og Siggi gerði
    @Override
    public User login(User user) {
        User doesExist = findByEmail(user.getEmail());
        if(doesExist != null) {
            if(doesExist.getPassword().equals(user.getPassword())) {
                return doesExist;
            }
        }
        return null;
    }

    @Override
    public List<HandyUser> findByFilter(String name, Trade trade, Double minRate, Double maxRate, boolean orderByRating) {
        if (name != null && trade == null && minRate == null && maxRate == null && !orderByRating) { //name
            return handyUserRepository.findByNameContainingIgnoreCase(name);
        }
        else if (name != null && trade == null && minRate == null && maxRate == null && orderByRating) { //name order
            return handyUserRepository.findByNameContainingIgnoreCaseOrderByAverageRatingDesc(name);
        }

        else if (name == null && trade != null && minRate == null && maxRate == null && !orderByRating) { //trade
            return handyUserRepository.findByTrade(trade);
        }
        else if (name == null && trade != null && minRate == null && maxRate == null && orderByRating) { //trade order
            return handyUserRepository.findByTradeOrderByAverageRatingDesc(trade);
        }

        else if (name == null && trade == null && minRate != null && maxRate == null && !orderByRating) { //minRate
            return handyUserRepository.findByHourlyRateGreaterThan(minRate);
        }
        else if (name == null && trade == null && minRate != null && maxRate == null && orderByRating) { //minRate order
            return handyUserRepository.findByHourlyRateGreaterThanOrderByAverageRatingDesc(minRate);
        }

        else if (name == null && trade == null && minRate == null && maxRate != null && !orderByRating) { //maxRate
            return handyUserRepository.findByHourlyRateLessThan(maxRate);
        }
        else if (name == null && trade == null && minRate == null && maxRate != null && orderByRating) { //maxRate order
            return handyUserRepository.findByHourlyRateLessThanOrderByAverageRatingDesc(maxRate);
        }

        else if (name != null && trade != null && minRate == null && maxRate == null && !orderByRating) { //name & trade
            return handyUserRepository.findByNameContainingIgnoreCaseAndTrade(name, trade);
        }
        else if (name != null && trade != null && minRate == null && maxRate == null && orderByRating) { //name & trade order
            return handyUserRepository.findByNameContainingIgnoreCaseAndTradeOrderByAverageRatingDesc(name, trade);
        }

        else if (name != null && trade == null && minRate != null && maxRate == null && !orderByRating) { //name & minRate
            return handyUserRepository.findByNameContainingIgnoreCaseAndHourlyRateGreaterThan(name, minRate);
        }
        else if (name != null && trade == null && minRate != null && maxRate == null && orderByRating) { //name & minRate order
            return handyUserRepository.findByNameContainingIgnoreCaseAndHourlyRateGreaterThanOrderByAverageRatingDesc(name, minRate);
        }

        else if (name != null && trade == null && minRate == null && maxRate != null && !orderByRating) { //name & maxRate
            return handyUserRepository.findByNameContainingIgnoreCaseAndHourlyRateLessThan(name, maxRate);
        }
        else if (name != null && trade == null && minRate == null && maxRate != null && orderByRating) { //name & maxRate order
            return handyUserRepository.findByNameContainingIgnoreCaseAndHourlyRateLessThanOrderByAverageRatingDesc(name, maxRate);
        }

        else if (name == null && trade != null && minRate != null && maxRate == null && !orderByRating) { //trade & minRate
            return handyUserRepository.findByTradeAndHourlyRateGreaterThan(trade, minRate);
        }
        else if (name == null && trade != null && minRate != null && maxRate == null && orderByRating) { //trade & minRate order
            return handyUserRepository.findByTradeAndHourlyRateGreaterThanOrderByAverageRatingDesc(trade, minRate);
        }

        else if (name == null && trade != null && minRate == null && maxRate != null && !orderByRating) { //trade & maxRate
            return handyUserRepository.findByTradeAndHourlyRateLessThan(trade, maxRate);
        }
        else if (name == null && trade != null && minRate == null && maxRate != null && orderByRating) { //trade & maxRate order
            return handyUserRepository.findByTradeAndHourlyRateLessThanOrderByAverageRatingDesc(trade, maxRate);
        }

        else if (name == null && trade == null && minRate != null && maxRate != null && !orderByRating) { //minRate & maxRate
            return handyUserRepository.findByHourlyRateBetween(minRate, maxRate);
        }
        else if (name == null && trade == null && minRate != null && maxRate != null && orderByRating) { //minRate & maxRate order
            return handyUserRepository.findByHourlyRateBetweenOrderByAverageRatingDesc(minRate, maxRate);
        }

        else if (name != null && trade != null && minRate != null && maxRate == null && !orderByRating) { //name,trade,minRate
            return handyUserRepository.findByNameContainingIgnoreCaseAndTradeAndHourlyRateGreaterThan(name, trade, minRate);
        }
        else if (name != null && trade != null && minRate != null && maxRate == null && orderByRating) { //name,trade,minRate order
            return handyUserRepository.findByNameContainingIgnoreCaseAndTradeAndHourlyRateGreaterThanOrderByAverageRatingDesc(name, trade, minRate);
        }

        else if (name != null && trade != null && minRate == null && maxRate != null && !orderByRating) { //name,trade,maxRate
            return handyUserRepository.findByNameContainingIgnoreCaseAndTradeAndHourlyRateLessThan(name, trade, maxRate);
        }
        else if (name != null && trade != null && minRate == null && maxRate != null && orderByRating) { //name,trade,maxRate order
            return handyUserRepository.findByNameContainingIgnoreCaseAndTradeAndHourlyRateLessThanOrderByAverageRatingDesc(name, trade, maxRate);
        }

        else if (name != null && trade == null && minRate != null && maxRate != null && !orderByRating) { //name,minRate,maxRate
            return handyUserRepository.findByNameContainingIgnoreCaseAndHourlyRateBetween(name, minRate, maxRate);
        }
        else if (name != null && trade == null && minRate != null && maxRate != null && orderByRating) { //name,minRate,maxRate order
            return handyUserRepository.findByNameContainingIgnoreCaseAndHourlyRateBetweenOrderByAverageRatingDesc(name, minRate, maxRate);
        }

        else if (name == null && trade != null && minRate != null && maxRate != null && !orderByRating) { //trade,minRate,maxRate
            return handyUserRepository.findByTradeAndHourlyRateBetween(trade, minRate, maxRate);
        }
        else if (name == null && trade != null && minRate != null && maxRate != null && orderByRating) { //trade,minRate,maxRate order
            return handyUserRepository.findByTradeAndHourlyRateBetweenOrderByAverageRatingDesc(trade, minRate, maxRate);
        }

        else if (name == null && trade == null && minRate == null && maxRate == null && !orderByRating) { //null
            return handyUserRepository.findAll();
        }
        else if (name == null && trade == null && minRate == null && maxRate == null && orderByRating) { //null order
            return handyUserRepository.findAllByOrderByAverageRatingDesc();
        }

        else if (name != null && trade != null && minRate != null && maxRate != null && !orderByRating) { //name,trade,minRate,maxRate
            return handyUserRepository.findByNameContainingIgnoreCaseAndTradeAndHourlyRateBetween(name, trade, minRate, maxRate);
        }
        else { //name,trade,minRate,maxRate order
            return handyUserRepository.findByNameContainingIgnoreCaseAndTradeAndHourlyRateBetweenOrderByAverageRatingDesc(name, trade, minRate, maxRate);
        }
    }
}
