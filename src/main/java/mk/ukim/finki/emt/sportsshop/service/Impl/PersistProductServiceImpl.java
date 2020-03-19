package mk.ukim.finki.emt.sportsshop.service.Impl;

import mk.ukim.finki.emt.sportsshop.models.Category;
import mk.ukim.finki.emt.sportsshop.models.Manufacturer;
import mk.ukim.finki.emt.sportsshop.models.Product;
import mk.ukim.finki.emt.sportsshop.models.Transactions;
import mk.ukim.finki.emt.sportsshop.repository.NewProductRepository;
import mk.ukim.finki.emt.sportsshop.repository.PersistCategoryRepository;
import mk.ukim.finki.emt.sportsshop.repository.PersistManufacturerRepository;
import mk.ukim.finki.emt.sportsshop.service.ProductService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Profile("persist")
@Service
public class PersistProductServiceImpl implements ProductService {

    private NewProductRepository productRepository;
    private PersistCategoryRepository categoryRepository;
    private PersistManufacturerRepository manufacturerRepository;

    public PersistProductServiceImpl(NewProductRepository productRepository, PersistCategoryRepository categoryRepository, PersistManufacturerRepository manufacturerRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long productId) {

        return productRepository.getById(productId);
    }

    @Override
    public Product addNewProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product addNewProduct(Long id, String name, String description, int price) {
        return null;
    }

    @Override
    public Manufacturer addNewManufacturer(Manufacturer manufacturer) {
        return manufacturerRepository.save(manufacturer);
    }

    @Override
    public Category addNewCategory(Category category) {
        return categoryRepository.save(category);
    }


    @Override
    public List<Product> findByCategory(Long categoryId) {
        return productRepository.findByCategory_Id(categoryId);
    }

    @Override
    public List<Product> findByCategoryAndManufacturer(Long categoryId, Long manId) {
        return productRepository.findAllByCategory_IdAndManufacturer_Id(categoryId,manId);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Manufacturer> getAllManufacturers() {
        return manufacturerRepository.findAll();
    }

    @Override
    public Double getSum(Long catId) {
        return productRepository.getSum(catId);
    }

}
