package com.lamchuong.r2sshop.controller;

import com.lamchuong.r2sshop.service.CategoryService;
import com.lamchuong.r2sshop.utils.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Object> getAllCategories() {
        try {
            return BaseResponseController.success(categoryService.getAllCategories());
        } catch (Exception e) {
            return BaseResponseController.fail(ResponseCode.UNKNOWN_ERROR);
        }
    }
}
