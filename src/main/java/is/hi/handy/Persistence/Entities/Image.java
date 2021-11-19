package is.hi.handy.Persistence.Entities;

import javax.persistence.*;

@Entity
@Table(name = "ImageProfile")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private long id;

    @Column(name = "Name")
    private String name;

    @Lob
    @Column(name = "Image")
    private byte[] image;

    @OneToOne(fetch = FetchType.LAZY)
    private PortfolioItem portfolioItem;

    public Image() {
        super();
        // TODO Auto-generated constructor stub
    }
    public Image(String name, byte[] image) {
        super();
        this.name = name;
        this.image = image;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
}