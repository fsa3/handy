package is.hi.handy.Services;

import is.hi.handy.Persistence.Entities.Image;

import java.util.List;

public interface ImageService {
    Image save(Image image);
    void delete(Image image);
    List<Image> findAll();
}
