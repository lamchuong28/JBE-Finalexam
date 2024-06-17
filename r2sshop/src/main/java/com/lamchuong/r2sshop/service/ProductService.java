package com.lamchuong.r2sshop.service;

import com.lamchuong.r2sshop.dto.request.ProductRequestDTO;
import com.lamchuong.r2sshop.dto.response.ProductResponseDTO;
import com.lamchuong.r2sshop.dto.response.VariantProductResponseDTO;
import com.lamchuong.r2sshop.exception.ProductNotFoundException;
import com.lamchuong.r2sshop.model.Category;
import com.lamchuong.r2sshop.model.Product;
import com.lamchuong.r2sshop.repository.CategoryRepository;
import com.lamchuong.r2sshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    //
    public List<ProductResponseDTO> getProductsByCategory(Long categoryId, int page, int size) {
        Page<Product> products = productRepository.findByCategoryId(categoryId, PageRequest.of(page, size));
        return products.stream().map(ProductResponseDTO::new).collect(Collectors.toList());
    }

    //
    public ProductResponseDTO getProductById(Long productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            return new ProductResponseDTO(productOptional.get());
        } else {
            throw new RuntimeException("Product not found");
        }
    }

    //
    public ProductResponseDTO addProduct(ProductRequestDTO productRequestDTO) {
        Category category = categoryRepository.findById(productRequestDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        Product product = new Product();
        product.setName(productRequestDTO.getName());
        product.setCategory(category);
        Product savedProduct = productRepository.save(product);
        return new ProductResponseDTO(savedProduct);
    }

    //
    public ProductResponseDTO updateProduct(Long productId, ProductRequestDTO productRequestDTO) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        Category category = categoryRepository.findById(productRequestDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        product.setName(productRequestDTO.getName());
        product.setCategory(category);
        Product updatedProduct = productRepository.save(product);
        return new ProductResponseDTO(updatedProduct);
    }


    //
    public List<VariantProductResponseDTO> getVarianProductByProductId(Long productId) throws ProductNotFoundException {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + productId));
        return product.getVariantProducts().stream().map(VariantProductResponseDTO::new).toList();
    }
}
