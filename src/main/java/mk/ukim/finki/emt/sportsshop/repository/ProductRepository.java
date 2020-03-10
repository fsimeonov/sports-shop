package mk.ukim.finki.emt.sportsshop.repository;

import mk.ukim.finki.emt.sportsshop.models.Category;
import mk.ukim.finki.emt.sportsshop.models.Manufacturer;
import mk.ukim.finki.emt.sportsshop.models.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductRepository {

    private Long counter;
    private List<Product> productList;
    private List<Category> categoryList = null;
    private List<Manufacturer> manufacturerList = null;

    private Long getNextId(){
        return counter++;
    }


    @PostConstruct
    public void init(){

        counter=1L;
        productList = new ArrayList<>();
        manufacturerList = new ArrayList<>();
        categoryList = new ArrayList<>();

        Category c1 = new Category();
        c1.setId(1L);
        c1.setName("Shirts");
        categoryList.add(c1);
        Category c2 = new Category();
        c2.setId(2L);
        c2.setName("Sleeves");
        categoryList.add(c2);
        Category c3 = new Category();
        c3.setId(3L);
        c3.setName("Bikes");
        categoryList.add(c3);
        Category c4 = new Category();
        c4.setId(4L);
        c4.setName("Sneakers");
        categoryList.add(c4);
        Category c5 = new Category();
        c5.setId(5L);
        c5.setName("Trainers");
        categoryList.add(c5);

        Manufacturer m1 = new Manufacturer();
        m1.setId(1L);
        m1.setName("Nike");
        manufacturerList.add(m1);
        Manufacturer m2 = new Manufacturer();
        m2.setId(2L);
        m2.setName("Adidas");
        manufacturerList.add(m2);
        Manufacturer m3 = new Manufacturer();
        m3.setId(3L);
        m3.setName("Puma");
        manufacturerList.add(m3);
        Manufacturer m4 = new Manufacturer();
        m4.setId(4L);
        m4.setName("Reebok");
        manufacturerList.add(m4);



        Product product=new Product();
        product.setId(counter);
        product.setName("Great trainers");
        product.setDescription("Great trainers with lowest price, come and buy today");
        product.setPictureLink("https://images.sportsdirect.com/images/products/21179840_4pl.jpg");
        product.setCategory(c5);
        product.setManufacturer(m2);
        product.setPrice(800);
        productList.add(product);

        Product product2 = new Product();
        product2.setId(getNextId());
        product2.setName("Sneakers on sale");
        product2.setDescription("great sneakers for hoops");
        product2.setPictureLink("https://i1.adis.ws/i/hibbett/Q0398_0001_right/Jordan%206%20Rings%20%22Black/White%22%20Men's%20Basketball%20Shoe-0001?$large$");
        product2.setCategory(c4);
        product2.setManufacturer(m1);
        product2.setPrice(900);
        productList.add(product2);
    }


    public Product save(Product product){
        product.setId(getNextId());
        productList.add(product);
        return product;
    }



    public Optional<Product> findById(Long id){
       return productList.stream().filter(temp -> temp.getId() == id).findAny();

    }

    public List<Product> getAllProducts(){
        return productList;
    }

    public void delete(Long id){
        productList.removeIf(temp -> temp.getId().equals(id));
    }

    public List<Product> findByCategory(Long categoryId){
        return productList.stream().filter(temp -> temp.getCategory().getId().equals(categoryId)).collect(Collectors.toList());
    }

    public List<Product> findByCategoryAndManufactrer(Long catId, Long manId){


        List<Product> products = productList.stream().filter(temp -> temp.getCategory().getId().equals(catId))
                .collect(Collectors.toList());

        return products.stream().filter(t -> t.getManufacturer().getId().equals(manId))
                .collect(Collectors.toList());
    }

    public Double getSum(Long catId){

        List<Product> products = productList.stream().filter(temp -> temp.getCategory().getId().equals(catId))
                .collect(Collectors.toList());

        return products.stream().mapToDouble(temp -> temp.getPrice()).sum();
    }


}
