package is.hi.handy.Services;

import is.hi.handy.Persistence.Entities.Message;
import is.hi.handy.Persistence.Entities.User;

import java.util.List;

public interface MessageService {
    Message save(Message message);
    void delete(Message message);
    List<Message> findAll();
    List<Message> findAllBySenderAndRecipient(User sender, User recipient);
    List<Message> findAllBySender(User sender);
    List<Message> findAllRecipient(User recipient);
    List<Message> findAllMessagesBetweenTwoUsers(User user1, User user2);
    Message getById(Long ID);
    List<Message> combineMessagesOrderByTime(List<Message> messages1, List<Message> messages2);
}
