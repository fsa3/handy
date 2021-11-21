package is.hi.handy.Persistence.Repositories;


import is.hi.handy.Persistence.Entities.Message;
import is.hi.handy.Persistence.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long> {
    Message save(Message message);
    void delete(Message message);
    List<Message> findAll();
    List<Message> findAllBySenderAndRecipient(User sender, User recipient);
    List<Message> findAllBySender(User sender);
    List<Message> findAllByRecipient(User recipient);
    Optional<Message> findById(Long id);


}

