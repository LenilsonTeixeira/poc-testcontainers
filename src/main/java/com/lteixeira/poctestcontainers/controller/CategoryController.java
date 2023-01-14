package com.lteixeira.poctestcontainers.controller;

import com.lteixeira.poctestcontainers.model.Category;
import com.lteixeira.poctestcontainers.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Category add(@RequestBody Category category) {
        return categoryService.save(category);
    }

    @GetMapping
    public List<Category> findAll() {
        return categoryService.findAll();
    }
}
