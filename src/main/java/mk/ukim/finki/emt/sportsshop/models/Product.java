package mk.ukim.finki.emt.sportsshop.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "productName")
    private String name;

    @Column(name = "productDescription")
    private String description;

    @Column(name = "pictureLink")
    private String pictureLink;

    @Column(name = "price")
    private int price;

    @ManyToOne
    @JoinColumn(name = "cat_id")
    @JsonIgnore
    private Category category;

    @ManyToOne
    @JoinColumn(name = "man_id")
    @JsonIgnore
    private Manufacturer manufacturer;

    @ManyToMany
    @JoinTable(name = "product_accessory",joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "accessory"))
    private List<Accessory> accessoryList;

    public List<Accessory> getAccessoryList() {
        return accessoryList;
    }

    public void setAccessoryList(List<Accessory> accessoryList) {
        this.accessoryList = accessoryList;
    }

    public Product(String name, String description, String pictureLink, int price, Category category, Manufacturer manufacturer) {
        this.name = name;
        this.description = description;
        this.pictureLink = pictureLink;
        this.price = price;
        this.category = category;
        this.manufacturer = manufacturer;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Product(){}


    public Long getId() {
        return id;
    }

    public String getPictureLink() {
        return pictureLink;
    }

    public void setPictureLink(String pictureLink) {
        this.pictureLink = pictureLink;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }
}
