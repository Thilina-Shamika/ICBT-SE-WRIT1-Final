package AbcRestaurantApp.controller;

import AbcRestaurantApp.entity.Products;
import AbcRestaurantApp.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/product")
    public Products postProduct(@RequestBody Products products){
        return productService.postProduct(products);
    }

    @GetMapping("/allproducts")
    public List<Products> getAllProducts(){
        return productService.getAllProducts();
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        try {
            productService.deleteProduct(id);
            return new ResponseEntity<>("User with id: " + id +  " Deleted successfully", HttpStatus.OK);
        }

        catch (EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id){
        Products products = productService.getProductById(id);
        if (products == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(products);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<?> updateProducts(@PathVariable Long id, @RequestBody Products products){
        Products updatedProducts = productService.updateProduct(id, products);

        if (updatedProducts == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.ok(updatedProducts);
    }

}
