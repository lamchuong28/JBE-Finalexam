package com.lamchuong.r2sshop.service;

import com.lamchuong.r2sshop.dto.request.VariantProductRequestDTO;
import com.lamchuong.r2sshop.dto.response.VariantProductResponseDTO;
import com.lamchuong.r2sshop.model.Product;
import com.lamchuong.r2sshop.model.VariantProduct;
import com.lamchuong.r2sshop.repository.ProductRepository;
import com.lamchuong.r2sshop.repository.VariantProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VariantProductService {

    @Autowired
    private VariantProductRepository variantProductRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<VariantProductResponseDTO> getVariantProductsByProductId(Long productId) {
        List<VariantProduct> variantProducts = variantProductRepository.findByProductId(productId);
        return variantProducts.stream().map(VariantProductResponseDTO::new).collect(Collectors.toList());
    }

    //
    public VariantProductResponseDTO addVariantProduct(VariantProductRequestDTO variantProductRequestDTO) {
        Product product = productRepository.findById(variantProductRequestDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        VariantProduct variantProduct = new VariantProduct();
        variantProduct.setColor(variantProductRequestDTO.getColor());
        variantProduct.setSize(variantProductRequestDTO.getSize());
        variantProduct.setPrice(variantProductRequestDTO.getPrice());
        variantProduct.setModel(variantProductRequestDTO.getModel());
        variantProduct.setProduct(product);
        VariantProduct savedVariantProduct = variantProductRepository.save(variantProduct);
        return new VariantProductResponseDTO(savedVariantProduct);
    }

    //
    public VariantProductResponseDTO updateVariantProduct(Long variantProductId, VariantProductRequestDTO variantProductRequestDTO) {
        VariantProduct variantProduct = variantProductRepository.findById(variantProductId)
                .orElseThrow(() -> new RuntimeException("VariantProduct not found"));
        Product product = productRepository.findById(variantProductRequestDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        variantProduct.setColor(variantProductRequestDTO.getColor());
        variantProduct.setSize(variantProductRequestDTO.getSize());
        variantProduct.setPrice(variantProductRequestDTO.getPrice());
        variantProduct.setModel(variantProductRequestDTO.getModel());
        variantProduct.setProduct(product);
        VariantProduct updatedVariantProduct = variantProductRepository.save(variantProduct);
        return new VariantProductResponseDTO(updatedVariantProduct);
    }
}
