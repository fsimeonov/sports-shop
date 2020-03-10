package mk.ukim.finki.emt.sportsshop.service.Impl;

import mk.ukim.finki.emt.sportsshop.models.Category;
import mk.ukim.finki.emt.sportsshop.models.Manufacturer;
import mk.ukim.finki.emt.sportsshop.models.Product;
import mk.ukim.finki.emt.sportsshop.repository.ProductRepository;
import mk.ukim.finki.emt.sportsshop.service.ProductService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Profile("in-memory")
@Service
public class ProductServiceImpl implements ProductService {

    ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Product> getAllProducts() {
        return repository.getAllProducts();
    }

    @Override
    public Product getProductById(Long productId) {
        return null;
    }

    @Override
    public Product addNewProduct(Product product) {
        return repository.save(product);
    }

    @Override
    public Manufacturer addNewManufacturer(Manufacturer manufacturer) {
        return null;
    }

    @Override
    public Category addNewCategory(Category category) {
        return null;
    }

    @Override
    public List<Product> findByCategory(Long categoryId) {
        return repository.findByCategory(categoryId);
    }

    @Override
    public List<Product> findByCategoryAndManufacturer(Long categoryId, Long manId) {
        return repository.findByCategoryAndManufactrer(categoryId,manId);
    }

    @Override
    public List<Category> getAllCategories() {
        return null;
    }

    @Override
    public List<Manufacturer> getAllManufacturers() {
        return null;
    }

    @Override
    public Double getSum(Long catId) {
        return repository.getSum(catId);
    }
}
