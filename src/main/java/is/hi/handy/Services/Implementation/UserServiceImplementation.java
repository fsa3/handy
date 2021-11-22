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
}
