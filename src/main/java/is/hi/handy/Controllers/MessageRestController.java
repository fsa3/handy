package is.hi.handy.Controllers;

import is.hi.handy.Persistence.Entities.Message;
import is.hi.handy.Persistence.Entities.User;
import is.hi.handy.Services.MessageService;
import is.hi.handy.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/api/messages", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Message> getMessages(@RequestParam(value = "user1", required = false) User user1, @RequestParam(value = "user2", required = false) User user2){
    List<Message> messages;
        if(user1 != null && user2 != null) {messages = getMessages(user1, user2);}
        else {messages = null;}


        return messages;
    }

}
