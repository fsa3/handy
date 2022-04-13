package is.hi.handy.Persistence.Entities;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "ImageProfile")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private long id;

    @Lob
    @Column(name = "Image")
    private byte[] image;

    @OneToOne(fetch = FetchType.LAZY)
    private PortfolioItem portfolioItem;

    @OneToOne(fetch = FetchType.LAZY)
    private Ad ad;

    public Image() {
        super();
        // TODO Auto-generated constructor stub
    }
    public Image(byte[] image) {
        super();
        this.image = image;
    }
    public Image(ArrayList<Byte> bytes) {
        int size = bytes.size();
        byte[] img = new byte[size];
        for (int i = 0; i < size; i++) {
            img[i] = bytes.get(i);
        }
        this.image = img;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public byte[] getImage() {
        return image;
    }
    public void setImage(byte[] image) {
        this.image = image;
    }

    public PortfolioItem getPortfolioItem() {
        return portfolioItem;
    }

    public void setPortfolioItem(PortfolioItem portfolioItem) {
        this.portfolioItem = portfolioItem;
    }

    public Ad getAd() {
        return ad;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }
}