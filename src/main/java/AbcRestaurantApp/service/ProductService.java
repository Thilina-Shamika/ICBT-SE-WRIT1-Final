package AbcRestaurantApp.service;

import AbcRestaurantApp.entity.Products;
import AbcRestaurantApp.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Products postProduct(Products products){
        return productRepository.save(products);
    }

    public List<Products> getAllProducts(){
        return productRepository.findAll();
    }

    public void deleteProduct(Long id){
        if (!productRepository.existsById(id)){
            throw new EntityNotFoundException("product with id: " + id + "not found");
        }
        productRepository.deleteById(id);
    }

    public Products getProductById(Long id){
        return productRepository.findById(id).orElse(null);
    }

    public Products updateProduct(Long id, Products products){
        Optional<Products> optionalProducts=productRepository.findById(id);

        if (optionalProducts.isPresent()){
            Products existingProducts = optionalProducts.get();
            existingProducts.setProdName(products.getProdName());
            existingProducts.setDescription(products.getDescription());
            existingProducts.setPrice(products.getPrice());

            return productRepository.save(existingProducts);
        }
        return null;
    }
}
