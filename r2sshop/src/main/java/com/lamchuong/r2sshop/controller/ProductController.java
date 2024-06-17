package com.lamchuong.r2sshop.controller;

import com.lamchuong.r2sshop.dto.request.ProductRequestDTO;
import com.lamchuong.r2sshop.dto.response.ProductResponseDTO;
import com.lamchuong.r2sshop.dto.response.VariantProductResponseDTO;
import com.lamchuong.r2sshop.exception.ProductNotFoundException;
import com.lamchuong.r2sshop.service.ProductService;
import com.lamchuong.r2sshop.service.VariantProductService;
import com.lamchuong.r2sshop.utils.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private VariantProductService variantProductService;

    //API: Get hết tất cả các product theo category. Áp dụng paging.
    @GetMapping("/by-category")
    public ResponseEntity<Object> getProductsByCategory(
            @RequestParam Long categoryId,
            @RequestParam int page,
            @RequestParam int size) {
        try {
            return BaseResponseController.success(productService.getProductsByCategory(categoryId, page, size));
        } catch (Exception e) {
            return BaseResponseController.fail(ResponseCode.UNKNOWN_ERROR);
        }
    }

    //API: Get thông tin chi tiết của product theo ID.
    @GetMapping("/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable Long productId) throws ProductNotFoundException {
        try {
            return BaseResponseController.success(productService.getProductById(productId));
        } catch (RuntimeException e) {
            return BaseResponseController.fail(ResponseCode.PRODUCT_NOT_FOUND.getCode(), e.getMessage());
        }
    }

    //Mỗi Product có nhiều VariantProduct khác nhau
    //API GET các variant product này.
    @GetMapping("/{productId}/variants")
    public ResponseEntity<Object> getVariantProductsByProductId(@PathVariable Long productId) {
        try {
            return BaseResponseController.success(variantProductService.getVariantProductsByProductId(productId));
        } catch (RuntimeException e) {
            return BaseResponseController.fail(ResponseCode.UNKNOWN_ERROR.getCode(), e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        ProductResponseDTO productResponseDTO = productService.addProduct(productRequestDTO);
        return BaseResponseController.success(productResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody ProductRequestDTO productRequestDTO) {
        ProductResponseDTO productResponseDTO = productService.updateProduct(id, productRequestDTO);
        return BaseResponseController.success(productResponseDTO);
    }
}
