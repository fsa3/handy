package is.hi.handy.Services.Implementation;

import is.hi.handy.Persistence.Entities.HandyUser;
import is.hi.handy.Persistence.Entities.Review;
import is.hi.handy.Persistence.Entities.User;
import is.hi.handy.Persistence.Repositories.ReviewRepository;
import is.hi.handy.Services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ReviewServiceImplementation implements ReviewService {
    private ReviewRepository repository;

    @Autowired
    public ReviewServiceImplementation(ReviewRepository repository) {
        this.repository = repository;
    }

    @Override
    public Review save(Review review) {
        return null;
    }

    @Override
    public void delete(Review review) {

    }

    @Override
    public List<Review> findAll() {
        return null;
    }

    @Override
    public List<Review> findAllOrderByIdDesc() {
        return null;
    }

    @Override
    public List<Review> findAllOrderByTimePosted() {
        return null;
    }

    @Override
    public Review findOne(Long id) {
        return null;
    }

    @Override
    public List<Review> findByText(String text) {
        return null;
    }

    @Override
    public List<Review> findByRatingGreaterThan(int rating) {
        return null;
    }

    @Override
    public List<Review> findByAuthor(User user) {
        return null;
    }

    @Override
    public List<Review> findByHandyman(HandyUser user) {
        return null;
    }

    @Override
    public List<Review> findByTimePostedGreaterThan(Timestamp time) {
        return null;
    }
}
