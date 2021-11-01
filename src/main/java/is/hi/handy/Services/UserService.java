package is.hi.handy.Services;

import is.hi.handy.Persistence.Entities.HandyUser;
import is.hi.handy.Persistence.Entities.User;

import java.util.List;

public interface UserService {
    User save(User user);
    HandyUser save(HandyUser user); //todo skoða að sameina aðferðir
    void delete(User user);
    void delete(HandyUser user); //todo skoða að sameina aðferðir

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
    List<HandyUser> orderHandyUserByRating(String trade, Double minRate, Double maxRate);

    // todo skilgreina þessa aðferð betur, veit ekki hvort það er rétt að gera þetta svona
    User login(User user);

}
