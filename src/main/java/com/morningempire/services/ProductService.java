package com.morningempire.services;


import com.morningempire.models.Product;
import com.morningempire.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    public Product saveProduct(Product product) {
        // Additional business logic can be added here
        return productRepository.save(product);
    }

    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }
    public Product findProduct(Long id) {
		Optional<Product> optionalProduct = productRepository.findById(id);
		if(optionalProduct.isPresent()) {
			return optionalProduct.get();
		} else {
			return null;
		}
		
	}
    // Other methods like updateProduct, findByCategory, etc. as needed
}