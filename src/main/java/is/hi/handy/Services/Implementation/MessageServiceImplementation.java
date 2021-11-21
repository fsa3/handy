package is.hi.handy.Services.Implementation;

import is.hi.handy.Persistence.Entities.Message;
import is.hi.handy.Persistence.Entities.User;
import is.hi.handy.Persistence.Repositories.MessageRepository;
import is.hi.handy.Services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<Message> findAllRecipient(User recipient) {
        return messageRepository.findAllByRecipient(recipient);
    }

    @Override
    public Message getById(Long ID) {
        return messageRepository.getById(ID);
    }

}
