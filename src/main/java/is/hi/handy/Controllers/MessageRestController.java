package is.hi.handy.Controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import is.hi.handy.Persistence.Entities.*;
import is.hi.handy.Services.MessageService;
import is.hi.handy.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MessageRestController {
    private MessageService messageService;
    private UserService userService;

    @Autowired
    public MessageRestController(MessageService messageService, UserService userService){
        this.messageService = messageService;
        this.userService = userService;
    }

    @RequestMapping(value = "/api/messages-between/{userId1}/{userId2}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Message> getMessagesBetweenUsers(@PathVariable long userId1, @PathVariable long userId2){
        List<Message> messages;
        User user1 = userService.findUser(userId1);
        User user2 = userService.findUser(userId2);
        messages = messageService.findAllMessagesBetweenTwoUsers(user1, user2);

        for (Message m : messages) {
            m.getSender().setAds(null);
            m.getRecipient().setAds(null);
        }

        return messages;
    }

    @RequestMapping(value = "/api/myMessages/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<User> getMyMessages(@PathVariable long userId){
        User user = userService.findUser(userId);
        List<User> userMessages = messageService.combineMessagesForUser(user);
        for (User u : userMessages) {
            u.setAds(null);
        }
        return userMessages;
    }

    @RequestMapping(value = "/api/message", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Message> sendMessage(@RequestBody ObjectNode json) {
        try {
            User sender = userService.findUser(json.get("senderId").asLong());
            User recipient = userService.findUser(json.get("recipientId").asLong());
            String content = json.get("content").asText();

            Message message = new Message(sender, recipient, content);
            Message savedMessage = messageService.save(message);

            savedMessage.getSender().setAds(null);
            savedMessage.getRecipient().setAds(null);

            return ResponseEntity.ok().body(savedMessage);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new Message());
        }
    }

}
