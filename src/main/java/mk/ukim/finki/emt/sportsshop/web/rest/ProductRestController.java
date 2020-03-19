package mk.ukim.finki.emt.sportsshop.web.rest;


import mk.ukim.finki.emt.sportsshop.models.Category;
import mk.ukim.finki.emt.sportsshop.models.Manufacturer;
import mk.ukim.finki.emt.sportsshop.models.Product;
import mk.ukim.finki.emt.sportsshop.models.exceptions.CategoryNotFoundException;
import mk.ukim.finki.emt.sportsshop.models.exceptions.ManufacturerNotFoundException;
import mk.ukim.finki.emt.sportsshop.models.exceptions.ProductNotFoundException;
import mk.ukim.finki.emt.sportsshop.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin("*")
@RestController
@RequestMapping("/rest")
public class ProductRestController {

    private ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @PostMapping("/products/add")
    public Product addProduct(@RequestBody Product product){
       return productService.addNewProduct(product);
    }


    @PostMapping("/manufacturer/add")
    public Manufacturer addManufacturer(@RequestBody Manufacturer manufacturer){
        return productService.addNewManufacturer(manufacturer);
    }

    @PostMapping("/category/add")
    public Category addCategory (@RequestBody Category category){
        return productService.addNewCategory(category);
    }

    @ExceptionHandler({ProductNotFoundException.class})
    @GetMapping("/products/{id}")
    public Product Show(@PathVariable("id") Long id){
//
//        productService.getAllProducts().stream()
//                .filter(temp -> temp.getId().equals(id)).findFirst()
//                .orElseThrow(() -> new ProductNotFoundException());

        return productService.getProductById(id);
    }

    @ExceptionHandler({CategoryNotFoundException.class})
    @GetMapping("/product/category/{categoryId}")
    public List<Product> ShowAllProductsInCategory(@PathVariable("categoryId") Long catId){

        productService.getAllProducts().stream()
                .filter(i->i.getCategory().getId().equals(catId)).findFirst()
                .orElseThrow(CategoryNotFoundException::new);

        return productService.findByCategory(catId);

    }

    @ExceptionHandler({ManufacturerNotFoundException.class, CategoryNotFoundException.class})
    @GetMapping("/product/category/{categoryId}/manufacturer/{manufacturerId}")
    public List<Product> ShowByCategoryAndManufacturer(@PathVariable("categoryId") Long categoryId, @PathVariable("manufacturerId")Long manId){

        productService.getAllProducts().stream()
                .filter(i -> i.getCategory().getId().equals(categoryId)).findFirst()
                .orElseThrow(CategoryNotFoundException::new);

        productService.getAllProducts().stream()
                .filter(i -> i.getManufacturer().getId().equals(manId)).findFirst()
                .orElseThrow(ManufacturerNotFoundException::new);

        return productService.findByCategoryAndManufacturer(categoryId,manId);

    }

    @ExceptionHandler({CategoryNotFoundException.class})
    @GetMapping("/product/category/{categoryId}/price")
    public Double ShowSum(@PathVariable("categoryId") Long id){
        productService.getAllProducts().stream()
                .filter(i -> i.getCategory().getId().equals(id)).findFirst()
                .orElseThrow(CategoryNotFoundException::new);

        return productService.getSum(id);
    }
}
