package is.hi.handy.Services;

import is.hi.handy.Persistence.Entities.HandyUser;
import is.hi.handy.Persistence.Entities.User;

import java.util.List;

public interface UserService {
    User save(User user);
    void delete(User user);

    List<User> findAllUsers();
    User findUser(Long id);
    User findByEmail(String email);
    List<User> findByName(String name);
    List<User> findByNameLike(String name);

    List<HandyUser> findAllHandyUser();
    HandyUser findOneHandyUser(Long id);
    HandyUser findHandyUserByEmail(String email);
    List<HandyUser> findHandyUserByName(String name);
    List<HandyUser> findHandyUserByNameLike(String name);
    List<HandyUser> findHandyUserByTrade(String trade);
    List<HandyUser> findHandyUserByRate(double minRate, double maxRate);

    // todo skilgreina þessa aðferð betur, veit ekki hvort það er rétt að gera þetta svona
    User login(String email, String password);
}