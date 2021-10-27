package is.hi.handy.Services;

import is.hi.handy.Persistence.Entities.HandyUser;
import is.hi.handy.Persistence.Entities.Review;
import is.hi.handy.Persistence.Entities.User;

import java.sql.Timestamp;
import java.util.List;

public interface ReviewService {
    Review save(Review review);
    void delete(Review review);
    List<Review> findAll();
    List<Review> findAllOrderByIdDesc();
    List<Review> findAllOrderByTimePosted();
    Review findOne(Long id);
    List<Review> findByText(String text);
    List<Review> findByRatingGreaterThan(int rating);
    List<Review> findByAuthor(User user);
    List<Review> findByHandyman(HandyUser user);
    List<Review> findByTimePostedGreaterThan(Timestamp time);
}
