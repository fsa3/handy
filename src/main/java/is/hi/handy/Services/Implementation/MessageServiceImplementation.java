package is.hi.handy.Services.Implementation;

import is.hi.handy.Persistence.Entities.Message;
import is.hi.handy.Persistence.Entities.User;
import is.hi.handy.Persistence.Repositories.MessageRepository;
import is.hi.handy.Services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Service
public class MessageServiceImplementation implements MessageService {
    MessageRepository messageRepository;

    @Autowired
    public MessageServiceImplementation(MessageRepository repository){this.messageRepository = repository;}

    @Override
    public Message save(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public void delete(Message message) { messageRepository.delete(message);}

    @Override
    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    @Override
    public List<Message> findAllBySenderAndRecipient(User sender, User recipient) {
        return messageRepository.findAllBySenderAndRecipient( sender, recipient);
    }

    @Override
    public List<Message> findAllBySender(User sender) {
        return messageRepository.findAllBySender(sender);
    }

    @Override
    public List<Message> findAllByRecipient(User recipient) {
        return messageRepository.findAllByRecipient(recipient);
    }

    @Override
    public List<Message> findAllMessagesBetweenTwoUsers(User user1, User user2) {
        List<Message> messagesFromUser1 = this.findAllBySenderAndRecipient(user1, user2);
        List<Message> messageToUser1 = this.findAllBySenderAndRecipient(user2, user1);
        return this.combineMessagesOrderByTime(messagesFromUser1, messageToUser1);
    }

    @Override
    public Message getById(Long ID) {
        return messageRepository.getById(ID);
    }

    @Override
    public List<Message> combineMessagesOrderByTime(List<Message> messages1, List<Message> messages2) {
        List<Message> messages = new ArrayList<>();
        messages.addAll(messages1);
        messages.addAll(messages2);
        Collections.sort(messages);
        return messages;
    }

}
