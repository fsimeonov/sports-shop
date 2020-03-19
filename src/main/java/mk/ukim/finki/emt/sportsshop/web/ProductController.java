package mk.ukim.finki.emt.sportsshop.web;

import mk.ukim.finki.emt.sportsshop.models.Category;
import mk.ukim.finki.emt.sportsshop.models.Manufacturer;
import mk.ukim.finki.emt.sportsshop.models.Product;
import mk.ukim.finki.emt.sportsshop.models.exceptions.CategoryNotFoundException;
import mk.ukim.finki.emt.sportsshop.models.exceptions.ManufacturerNotFoundException;
import mk.ukim.finki.emt.sportsshop.models.exceptions.ProductNotFoundException;
import mk.ukim.finki.emt.sportsshop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;


@Controller
@RequestMapping("/")
public class ProductController {


    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String products(Model model) {
        model.addAttribute("productList", productService.getAllProducts());
        model.addAttribute("manufacturers", productService.getAllManufacturers());
        model.addAttribute("categories", productService.getAllCategories());
        model.addAttribute("product", new Product());
        return "products";
    }


    @GetMapping("/products/add")
    public String addProduct(Model model) {
        model.addAttribute("categoryList", productService.getAllCategories());
        model.addAttribute("manufacturerList", productService.getAllManufacturers());
        model.addAttribute("productList", productService.getAllProducts());
        model.addAttribute("product", new Product());
        return "addNewProduct";
    }

    @ExceptionHandler({ProductNotFoundException.class})
    @GetMapping("products/{id}")
    public String getProductDataById(@PathVariable("id") Long id, Model model) {

        Product product = productService.getAllProducts().stream()
                .filter(i -> i.getId().equals(id)).findFirst().orElseThrow(ProductNotFoundException::new);
        model.addAttribute("product", product);
        return "product-info";
    }

    @ExceptionHandler({ManufacturerNotFoundException.class, CategoryNotFoundException.class})
    @PostMapping("/products")
    public String addProduct(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        String id = request.getParameter("id");
        String name = request.getParameter("name"); //test name
        String description = request.getParameter("description"); //test desc
        String manufacturer = request.getParameter("manufacturer"); //nike
        String category = request.getParameter("category"); //trainers
        String pictureLink = request.getParameter("pictureLink");
        int price = Integer.parseInt(request.getParameter("price")); //111

        Optional<Manufacturer> man = productService.getAllManufacturers().stream()
                .filter(i -> i.getName().equals(manufacturer)).findAny();
        if (!man.isPresent()) {
            throw new ManufacturerNotFoundException();
        }
        Optional<Category> cat = productService.getAllCategories().stream()
                .filter(i -> i.getName().equals(category)).findAny();
        if (!cat.isPresent()) {
            throw new CategoryNotFoundException();
        }

        Product p = new Product();
        p.setId(Long.parseLong(id));
        p.setPictureLink(pictureLink);
        p.setName(name);
        p.setDescription(description);
        p.setManufacturer(man.get());
        p.setCategory(cat.get());
        p.setPrice(price);

        productService.addNewProduct(p);
        //productService.addNewProduct(Long.parseLong(id),name,description,price);
       // model.addAttribute("productList", productService.getAllProducts());
        return "redirect:/products/";
        // response.sendRedirect("http://localhost:9090/products");
    }

}