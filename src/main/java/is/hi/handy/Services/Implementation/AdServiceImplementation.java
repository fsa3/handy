package is.hi.handy.Services.Implementation;

import is.hi.handy.Persistence.Entities.Ad;
import is.hi.handy.Persistence.Entities.User;
import is.hi.handy.Persistence.Repositories.AdRepository;
import is.hi.handy.Services.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class AdServiceImplementation implements AdService {
    AdRepository repository;

    @Autowired
    public AdServiceImplementation(AdRepository repository) {
        this.repository = repository;
    }

    @Override
    public Ad save(Ad ad) {
        return repository.save(ad);
    }

    @Override
    public void delete(Ad ad) {
        repository.delete(ad);
    }

    @Override
    public List<Ad> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Ad> findAllOrderByIdDesc() {
        return repository.findAllByOrderByIDDesc();
    }

    @Override
    public List<Ad> findAllOrderByTimePostedDesc() {
        return repository.findAllByOrderByTimePostedDesc();
    }

    @Override
    public Ad findOne(Long id) {
        return repository.findByID(id);
    }

    @Override
    public List<Ad> findByTitle(String title) {
        return repository.findByTitle(title);
    }

    @Override
    public List<Ad> findByUser(User user) {
        return repository.findByUser(user);
    }

    @Override
    public List<Ad> findByDescription(String description) {
        return repository.findByDescription(description);
    }

    @Override
    public List<Ad> findByLocation(String location) {
        return null;
    }

    @Override
    public List<Ad> findByTimePostedGreaterThan(Timestamp timestamp) {
        return repository.findByTimePostedGreaterThan(timestamp);
    }

}
