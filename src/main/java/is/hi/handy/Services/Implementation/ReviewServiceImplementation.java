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
        return repository.save(review);
    }

    @Override
    public void delete(Review review) {
        repository.delete(review);
    }

    @Override
    public List<Review> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Review> findAllOrderByIdDesc() {
        return repository.findAllByOrderByIDDesc();
    }

    @Override
    public List<Review> findAllOrderByTimePosted() {
        return repository.findAllByOrderByTimePostedDesc();
    }

    @Override
    public Review findOne(Long id) {
        return repository.findByID(id);
    }

    @Override
    public List<Review> findByText(String text) {
        return repository.findByText(text);
    }

    @Override
    public List<Review> findByRatingGreaterThan(int rating) {
        return repository.findByRatingGreaterThan(rating);
    }

    @Override
    public List<Review> findByAuthor(User user) {
        return repository.findByAuthor(user);
    }

    @Override
    public List<Review> findByHandyman(HandyUser user) {
        return repository.findByHandyman(user);
    }

    @Override
    public List<Review> findByTimePostedGreaterThan(Timestamp time) {
        return repository.findByTimePostedGreaterThan(time);
    }
}
