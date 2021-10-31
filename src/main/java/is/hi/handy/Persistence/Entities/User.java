package is.hi.handy.Persistence.Entities;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long ID;
    protected String name;
    protected String email;
    protected String info;
    protected String password;

    @OneToMany(mappedBy = "user")
    private List<Ad> advertisements = new ArrayList<>();
    //todo messages

    public User(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Ad> getAds() {
        return advertisements;
    }

    public void setAds(List<Ad> ads) {
        this.advertisements = ads;
    }

    public long getID() {
        return ID;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setID(long ID) {
        this.ID = ID;
    }
}
