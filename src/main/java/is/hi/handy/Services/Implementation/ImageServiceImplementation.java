package is.hi.handy.Services.Implementation;

import is.hi.handy.Persistence.Entities.Image;
import is.hi.handy.Persistence.Repositories.ImageRepository;
import is.hi.handy.Services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImplementation implements ImageService {
    ImageRepository repository;

    @Autowired
    public ImageServiceImplementation(ImageRepository repository) {
        this.repository = repository;
    }

    @Override
    public Image save(Image image) {
        return repository.save(image);
    }

    @Override
    public void delete(Image image) {
        repository.delete(image);
    }

    @Override
    public List<Image> findAll() {
        return repository.findAll();
    }
}
