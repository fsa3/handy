package is.hi.handy.Services.Implementation;

import is.hi.handy.Persistence.Entities.Ad;
import is.hi.handy.Persistence.Entities.User;
import is.hi.handy.Services.AdService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class AdServiceImplementation implements AdService {
    @Override
    public Ad save(Ad ad) {
        return null;
    }

    @Override
    public void delete(Ad ad) {

    }

    @Override
    public List<Ad> findAll() {
        return null;
    }

    @Override
    public List<Ad> findAllOrderByIdDesc() {
        return null;
    }

    @Override
    public List<Ad> findAllOrderByTimePostedDesc() {
        return null;
    }

    @Override
    public Ad findOne(Long id) {
        return null;
    }

    @Override
    public List<Ad> findByTitle(String title) {
        return null;
    }

    @Override
    public List<Ad> findByUser(User user) {
        return null;
    }

    @Override
    public List<Ad> findByDescription(String description) {
        return null;
    }

    @Override
    public List<Ad> findByLocation(String location) {
        return null;
    }

    @Override
    public List<Ad> findByTimePostedGreaterThan(Timestamp timestamp) {
        return null;
    }
    //private AdRepository adRepository;


}
