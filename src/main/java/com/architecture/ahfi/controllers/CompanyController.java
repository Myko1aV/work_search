package com.architecture.ahfi.controllers;


import com.architecture.ahfi.entities.Company;
import com.architecture.ahfi.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/company")
public class CompanyController {
    final
    CompanyService service;

    public CompanyController(CompanyService service) {
        this.service = service;
    }

    @GetMapping("")
    List<Company> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<Company> getOne(@PathVariable Integer id) {
        try {
            Company Company = service.getById(id);
            return new ResponseEntity<>(Company, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
