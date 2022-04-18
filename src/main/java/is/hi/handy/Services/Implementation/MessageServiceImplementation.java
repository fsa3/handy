package is.hi.handy.Services.Implementation;

import com.pusher.pushnotifications.PushNotifications;
import is.hi.handy.HandyApplication;
import is.hi.handy.Persistence.Entities.Message;
import is.hi.handy.Persistence.Entities.User;
import is.hi.handy.Persistence.Repositories.MessageRepository;
import is.hi.handy.Services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

@Service
public class MessageServiceImplementation implements MessageService {
    MessageRepository messageRepository;
    PushNotifications beamsClient = new PushNotifications(HandyApplication.instanceId, HandyApplication.secretKey);

    @Autowired
    public MessageServiceImplementation(MessageRepository repository) {this.messageRepository = repository;}

    @Override
    public Message save(Message message) {

        List<String> interests = List.of(message.getRecipient().getEmail());

        Map<String, Map> publishRequest = new HashMap();

        Map<String, String> apsAlert = new HashMap();
        apsAlert.put("title", "New message from " + message.getSender().getName());
        apsAlert.put("body", message.getContent());
        Map<String, Map> alert = new HashMap();
        alert.put("alert", apsAlert);
        Map<String, Map> aps = new HashMap();
        aps.put("aps", alert);
        publishRequest.put("apns", aps);

        Map<String, String> fcmNotification = new HashMap();
        fcmNotification.put("title", "New message from " + message.getSender().getName());
        fcmNotification.put("body", message.getContent());
        Map<String, Map> fcm = new HashMap();
        fcm.put("notification", fcmNotification);
        publishRequest.put("fcm", fcm);

        Map<String, String> webNotification = new HashMap();
        webNotification.put("title", "New message from " + message.getSender().getName());
        webNotification.put("body", message.getContent());
        Map<String, Map> web = new HashMap();
        web.put("notification", webNotification);
        publishRequest.put("web", web);

        try {
            beamsClient.publishToInterests(interests, publishRequest);
        } catch (IOException | InterruptedException | URISyntaxException e) {
            e.printStackTrace();
        }

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
    @Override
    public List<User> combineMessagesForUser(User user){

        Set<Message> messageSet1 = new HashSet<>();
        messageSet1.addAll(messageRepository.findAllByRecipient(user));
        List<Message> messages1 = new ArrayList<>(messageSet1);

        Set<Message> messageSet2 = new HashSet<>();
        messageSet2.addAll(messageRepository.findAllBySender(user));
        List<Message> messages2 = new ArrayList<>(messageSet2);

        Set<User> userSet = new HashSet<>();

        for(Message message: messages1){
            userSet.add(message.getSender());
        }

        for(Message message: messages2){
            userSet.add(message.getRecipient());
        }
        System.out.println(userSet);
        List<User> MessagedUsers = new ArrayList<>(userSet);
        System.out.println(MessagedUsers);
        return MessagedUsers;
    }

}
