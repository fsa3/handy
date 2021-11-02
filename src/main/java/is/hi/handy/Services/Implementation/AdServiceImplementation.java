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
    AdRepository adRepository;

    @Autowired
    public AdServiceImplementation(AdRepository repository) {
        this.adRepository = repository;
    }

    @Override
    public Ad save(Ad ad) {
        return adRepository.save(ad);
    }

    @Override
    public void delete(Ad ad) {
        adRepository.delete(ad);
    }

    @Override
    public List<Ad> findAll() {
        return adRepository.findAll();
    }

    @Override
    public List<Ad> findAllOrderByIdDesc() {
        return adRepository.findAllByOrderByIDDesc();
    }

    @Override
    public List<Ad> findAllOrderByTimePostedDesc() {
        return adRepository.findAllByOrderByTimePostedDesc();
    }

    @Override
    public Ad findOne(Long id) {
        return adRepository.findByID(id);
    }

    @Override
    public List<Ad> findByTitle(String title) {
        return adRepository.findByTitle(title);
    }

    @Override
    public List<Ad> findByUser(User user) {
        return adRepository.findByUser(user);
    }

    @Override
    public List<Ad> findByDescription(String description) {
        return adRepository.findByDescription(description);
    }

    @Override
    public List<Ad> findByLocation(String location) {
        return null;
    }

    @Override
    public List<Ad> findByTimePostedGreaterThan(Timestamp timestamp) {
        return adRepository.findByTimePostedGreaterThan(timestamp);
    }

}
