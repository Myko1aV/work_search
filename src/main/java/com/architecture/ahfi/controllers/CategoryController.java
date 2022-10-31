package com.architecture.ahfi.controllers;


import com.architecture.ahfi.entities.Category;
import com.architecture.ahfi.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    CategoryService service;

    @GetMapping("")
    List<Category> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<Category> getOne(@PathVariable Integer id) {
        try {
            Category category = service.getById(id);
            return new ResponseEntity<>(category, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
