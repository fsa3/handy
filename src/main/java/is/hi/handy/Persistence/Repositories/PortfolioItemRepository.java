package is.hi.handy.Persistence.Repositories;

import is.hi.handy.Persistence.Entities.PortfolioItem;
import is.hi.handy.Persistence.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PortfolioItemRepository extends JpaRepository<PortfolioItem, Long> {
    PortfolioItem save(PortfolioItem portfolioItem);
    void delete(PortfolioItem portfolioItem);

    List<PortfolioItem> findAll();
    PortfolioItem findByID(long ID);
    PortfolioItem findByTitle(String title);
    List<PortfolioItem> findByUsername(String username);
}
