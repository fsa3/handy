package is.hi.handy.Persistence.Entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Entity
@Table(name = "messages")
public class Message implements Comparable<Message> {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
    @ManyToOne(fetch = FetchType.LAZY)
    private User sender;
    @ManyToOne(fetch= FetchType.LAZY)
    private User recipient;
    private String content;
    private String subjectHeading;
    private Timestamp timePosted;
    private String timeString;


    public Message(User sender, User recipient, String content) {
        this.sender = sender;
        this.recipient = recipient;
        this.content = content;
        this.setTimePosted(new Timestamp(System.currentTimeMillis()));
    }

    public Message() {
        this.setTimePosted(new Timestamp(System.currentTimeMillis()));
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSubjectHeading() {
        return subjectHeading;
    }

    public void setSubjectHeading(String subjectHeading) {
        this.subjectHeading = subjectHeading;
    }

    public Timestamp getTimePosted() {
        return timePosted;
    }

    public void setTimePosted(Timestamp timePosted) {
        this.timePosted = timePosted;
        this.timeString = new SimpleDateFormat("dd.MM.yy HH:mm").format(timePosted);
    }

    public String getTimeString() {
        return timeString;
    }

    @Override
    public int compareTo(Message o) {
        return this.timePosted.compareTo(o.timePosted);
    }
}
