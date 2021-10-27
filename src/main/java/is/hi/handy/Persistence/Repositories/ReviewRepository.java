package is.hi.handy.Persistence.Repositories;

import is.hi.handy.Persistence.Entities.HandyUser;
import is.hi.handy.Persistence.Entities.Review;
import is.hi.handy.Persistence.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface ReviewRepository extends JpaRepository {
    Review save(Review review);
    void delete(Review review);
    List<Review> findAll();
    List<Review> findAllOrderByIdDesc();
    List<Review> findAllOrderByTimePostedDesc();
    Review findByID(Long id);
    List<Review> findByText(String text);
    List<Review> findByRatingGreaterThan(int rating);
    List<Review> findByAuthor(User author);
    List<Review> findByHandyman(HandyUser user);
    List<Review> findByTimePostedGreaterThan(Timestamp time);
}
