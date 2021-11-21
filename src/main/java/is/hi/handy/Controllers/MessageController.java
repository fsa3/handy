package is.hi.handy.Controllers;

import is.hi.handy.Persistence.Entities.HandyUser;
import is.hi.handy.Persistence.Entities.Message;
import is.hi.handy.Persistence.Entities.Review;
import is.hi.handy.Persistence.Entities.User;
import is.hi.handy.Services.MessageService;
import is.hi.handy.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MessageController {
    private MessageService messageService;
    private UserService userService;


    @Autowired
    public MessageController(MessageService messageService, UserService userService){
        this.messageService = messageService;
        this.userService = userService;
    }
/*
    @RequestMapping("mymessages/{id}")
    public String getMessages(@PathVariable("id") long id, Model model, HttpSession session){
        model.addAttribute("LoggedInUser", session.getAttribute("LoggedInUser"));

        List<Message> messages = messageService.findAllBySender((User) session.getAttribute("LoggedInUser"));
        System.out.println(messages);
        model.addAttribute("messages", messages);

        return "mymessages";
 */
@RequestMapping(value = "/messageForm/{handyUserId}", method = RequestMethod.GET)
public String messageForm(Model model, HttpSession session, @PathVariable("handyUserId") long userId) {
    User loggedInUser = (User) session.getAttribute("LoggedInUser");
    if(loggedInUser != null) {
        model.addAttribute("LoggedInUser", loggedInUser);
        User recipient = userService.findUser(userId);
        model.addAttribute("recipient", recipient);
        List<Message> messages = messageService.findAllBySenderAndRecipient(loggedInUser, recipient);
        model.addAttribute("message", messages);
        return "messageForm";
    }
    return "redirect:/login";
}

 @RequestMapping(value = "sendMessage/{handyUserId}", method = RequestMethod.POST)
    public String save(Model model, Message message, @PathVariable("handyUserId") long userId, HttpSession session) {
        User recipient = userService.findUser(userId);
        User loggedInUser = (User) session.getAttribute("LoggedInUser");
        message.setSender(loggedInUser);
        message.setRecipient(recipient);
        messageService.save(message);
        return "redirect:/messageForm/" + userId;
    }

// hér á eftir að búa til mymessages.
    }

  //  @RequestMapping(/)

