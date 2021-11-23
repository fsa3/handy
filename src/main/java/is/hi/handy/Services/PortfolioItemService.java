package is.hi.handy.Services;

import is.hi.handy.Persistence.Entities.HandyUser;
import is.hi.handy.Persistence.Entities.PortfolioItem;

import java.util.List;

public interface PortfolioItemService {
    PortfolioItem save(PortfolioItem portfolioItem);
    void delete(PortfolioItem portfolioItem);
    List<PortfolioItem> findAll();
    PortfolioItem findByID(long ID);
    PortfolioItem findByTitle(String title);
    List<PortfolioItem> findByHandyUser(HandyUser handyUser);

}
