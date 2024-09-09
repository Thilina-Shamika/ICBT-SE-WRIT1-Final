package AbcRestaurantApp.service;

import AbcRestaurantApp.entity.Products;
import AbcRestaurantApp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    private Path root;

    @Autowired
    public void init() {
        this.root = Paths.get(uploadDir);
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    public Products postProduct(Products product) {
        return productRepository.save(product);
    }

    public List<Products> getAllProducts() {
        return productRepository.findAll();
    }

    public Products getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public void deleteProduct(Long id) {
        Optional<Products> product = productRepository.findById(id);
        if (product.isPresent()) {
            // Delete the image file
            String imageName = product.get().getImageName();
            if (imageName != null) {
                try {
                    Files.deleteIfExists(this.root.resolve(imageName));
                } catch (IOException e) {
                    throw new RuntimeException("Error: " + e.getMessage());
                }
            }
            // Delete the product from the database
            productRepository.deleteById(id);
        } else {
            throw new RuntimeException("Product not found with id " + id);
        }
    }

    public Products updateProduct(Long id, Products updatedProduct) {
        Optional<Products> existingProductOptional = productRepository.findById(id);
        if (existingProductOptional.isPresent()) {
            Products existingProduct = existingProductOptional.get();
            existingProduct.setProdName(updatedProduct.getProdName());
            existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setPrice(updatedProduct.getPrice());
            // Note: We're not updating the image here. If you want to update the image,
            // you'll need to use the updateProductWithImage method
            return productRepository.save(existingProduct);
        }
        return null;
    }

    public Products updateProductWithImage(Long id, Products updatedProduct, MultipartFile image) throws IOException {
        Optional<Products> existingProductOptional = productRepository.findById(id);
        if (existingProductOptional.isPresent()) {
            Products existingProduct = existingProductOptional.get();
            existingProduct.setProdName(updatedProduct.getProdName());
            existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setPrice(updatedProduct.getPrice());

            // Delete old image if it exists
            if (existingProduct.getImageName() != null) {
                Files.deleteIfExists(this.root.resolve(existingProduct.getImageName()));
            }

            // Save new image
            String filename = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
            Files.copy(image.getInputStream(), this.root.resolve(filename));
            existingProduct.setImageName(filename);

            return productRepository.save(existingProduct);
        }
        return null;
    }
}