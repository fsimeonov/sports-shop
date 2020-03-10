package mk.ukim.finki.emt.sportsshop.service;

import mk.ukim.finki.emt.sportsshop.models.Category;
import mk.ukim.finki.emt.sportsshop.models.Manufacturer;
import mk.ukim.finki.emt.sportsshop.models.Product;
import mk.ukim.finki.emt.sportsshop.models.Transactions;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long productId);
    Product addNewProduct(Product product);
    Manufacturer addNewManufacturer(Manufacturer manufacturer);
    Category addNewCategory(Category category);
    List<Product> findByCategory(Long categoryId);
    List<Product> findByCategoryAndManufacturer(Long categoryId, Long manId);
    List<Category> getAllCategories();
    List<Manufacturer> getAllManufacturers();
    Double getSum(Long catId);
}
