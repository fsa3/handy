package is.hi.handy.Persistence.Repositories;

import is.hi.handy.Persistence.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User save(User user);
    void delete(User user);
    List<User> findAll();
    User findByID(Long id);
    User findByEmail(String email);
    List<User> findByName(String name);
    List<User> findByNameLike(String name);
}
