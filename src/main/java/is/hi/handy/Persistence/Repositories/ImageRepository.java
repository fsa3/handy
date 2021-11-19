package is.hi.handy.Persistence.Repositories;

import is.hi.handy.Persistence.Entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Image save(Image image);
    void delete(Image image);
    List<Image> findAll();
}
