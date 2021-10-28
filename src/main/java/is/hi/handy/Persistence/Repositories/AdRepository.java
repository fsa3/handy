package is.hi.handy.Persistence.Repositories;

import is.hi.handy.Persistence.Entities.Ad;
import is.hi.handy.Persistence.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface AdRepository extends JpaRepository<Ad, Long> {

    Ad save(Ad ad);
    void delete(Ad ad);
    List<Ad> findAll();
    List<Ad> findAllOrderByIdDesc();
    List<Ad> findAllOrderByTimePostedDesc();
    Ad findOne(Long ID);
    List<Ad> findByTitle(String title);
    List<Ad> findByUser(User user);
    List<Ad> findByDescription(String description);
    List<Ad> findByLocation(String location);
    List<Ad> findByTimePostedGreaterThan(Timestamp timestamp);

}
