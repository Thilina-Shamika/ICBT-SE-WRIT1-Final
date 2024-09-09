package AbcRestaurantApp.controller;

import AbcRestaurantApp.entity.Products;
import AbcRestaurantApp.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/product")
    public ResponseEntity<Products> postProduct(
            @RequestParam("prodName") String prodName,
            @RequestParam("description") String description,
            @RequestParam("price") Integer price,
            @RequestParam("image") MultipartFile image) {
        try {
            Products product = new Products();
            product.setProdName(prodName);
            product.setDescription(description);
            product.setPrice(price);

            Products savedProduct = productService.postProductWithImage(product, image);
            return ResponseEntity.ok(savedProduct);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/allproducts")
    public List<Products> getAllProducts() {
        return productService.getAllProducts();
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
            return new ResponseEntity<>("Product with id: " + id + " deleted successfully", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        Products product = productService.getProductById(id);
        if (product == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(product);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<?> updateProducts(@PathVariable Long id, @RequestBody Products products) {
        Products updatedProduct = productService.updateProduct(id, products);
        if (updatedProduct == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.ok(updatedProduct);
    }
}