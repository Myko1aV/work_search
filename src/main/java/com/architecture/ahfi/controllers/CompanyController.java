package com.architecture.ahfi.controllers;


import com.architecture.ahfi.entities.Company;
import com.architecture.ahfi.entities.Response;
import com.architecture.ahfi.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.naming.CompositeName;
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
    //cтв ред вид
    @PutMapping("/{id}")
    ResponseEntity<?> updateCompany(@PathVariable Integer id, @RequestBody Company company) {
        try {
            service.getById(id);
            company.setId(id);
            service.save(company);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("")
    ResponseEntity<?> addCompany(@RequestBody Company company) {
        try {
            service.save(company);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (HttpClientErrorException.BadRequest e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteCompany(@PathVariable Integer id) {
        try {
            service.getById(id);
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
