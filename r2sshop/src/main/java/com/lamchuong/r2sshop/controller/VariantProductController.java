package com.lamchuong.r2sshop.controller;

import com.lamchuong.r2sshop.dto.request.VariantProductRequestDTO;
import com.lamchuong.r2sshop.dto.response.VariantProductResponseDTO;
import com.lamchuong.r2sshop.service.VariantProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/variant-products")
public class VariantProductController {

    @Autowired
    private VariantProductService variantProductService;

    @PostMapping
    public ResponseEntity<?> addVariantProduct(@RequestBody VariantProductRequestDTO variantProductRequestDTO) {
        VariantProductResponseDTO variantProductResponseDTO = variantProductService.addVariantProduct(variantProductRequestDTO);
        return BaseResponseController.success(variantProductResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateVariantProduct(@PathVariable Long id, @RequestBody VariantProductRequestDTO variantProductRequestDTO) {
        VariantProductResponseDTO variantProductResponseDTO = variantProductService.updateVariantProduct(id, variantProductRequestDTO);
        return BaseResponseController.success(variantProductResponseDTO);
    }
}
