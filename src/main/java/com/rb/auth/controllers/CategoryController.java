package com.rb.auth.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.rb.auth.domain.enums.Category;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @GetMapping
    public ResponseEntity<List<String>> getCategories() {
        var categories = Arrays.stream(Category.values())
                .map(Category::getCategory)
                .collect(Collectors.toList());

        return ResponseEntity.ok(categories);
    }
    
}
