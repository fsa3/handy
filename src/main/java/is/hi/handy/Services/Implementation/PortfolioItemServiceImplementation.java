package is.hi.handy.Services.Implementation;

import is.hi.handy.Persistence.Entities.HandyUser;
import is.hi.handy.Persistence.Entities.PortfolioItem;
import is.hi.handy.Persistence.Repositories.PortfolioItemRepository;
import is.hi.handy.Services.PortfolioItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortfolioItemServiceImplementation implements PortfolioItemService {
    private PortfolioItemRepository portfolioItemRepository;

    @Autowired
    public PortfolioItemServiceImplementation(PortfolioItemRepository portfolioItemRepository){
        this.portfolioItemRepository = portfolioItemRepository;
    }

    @Override
    public PortfolioItem save(PortfolioItem portfolioItem) {
        return portfolioItemRepository.save(portfolioItem);
    }

    @Override
    public void delete(PortfolioItem portfolioItem) {
        portfolioItemRepository.delete(portfolioItem);
    }

    @Override
    public List<PortfolioItem> findAll() {
        return portfolioItemRepository.findAll();
    }

    @Override
    public PortfolioItem findByID(long ID) {
        return portfolioItemRepository.findByID(ID);
    }

    @Override
    public PortfolioItem findByTitle(String title) {
        return portfolioItemRepository.findByTitle(title);
    }

    @Override
    public List<PortfolioItem> findByHandyUser(HandyUser handyUser) {
        return portfolioItemRepository.findByUsername(handyUser.getName());
    }
}
