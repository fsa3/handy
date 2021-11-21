package is.hi.handy.Controllers;

import is.hi.handy.Persistence.Entities.Message;
import is.hi.handy.Persistence.Entities.User;
import is.hi.handy.Services.MessageService;
import is.hi.handy.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("mymessages/{id}")
    public String getMessages(@PathVariable("id") long id, Model model, HttpSession session){
        model.addAttribute("LoggedInUser", session.getAttribute("LoggedInUser"));

        List<Message> messages = messageService.findAllBySender((User) session.getAttribute("LoggedInUser"));
        System.out.println(messages);
        model.addAttribute("messages", messages);

        return "mymessages";

// hér á eftir að búa til mymessages.
    }

  //  @RequestMapping(/)

}