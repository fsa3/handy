package is.hi.handy.Persistence.Entities;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
    private String email;
    private String Info;
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Ad> adsvertisements = new ArrayList<>();
    //todo messages

    public User(){
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
        return adsvertisements;
    }

    public void setAds(List<Ad> ads) {
        this.adsvertisements = ads;
    }
}
