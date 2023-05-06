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

//    @GetMapping("/{id}")
//    ResponseEntity<Company> getOne(@PathVariable Integer id) {
//        try {
//            Company Company = service.getById(id);
//            return new ResponseEntity<>(Company, HttpStatus.OK);
//        } catch (NoSuchElementException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
    @GetMapping("/{id}")
    Company getOne(@PathVariable Integer id) {
        try {
            Company Company = service.getById(id);
            return Company;
        } catch (NoSuchElementException e) {
            return null;
        }
    }
    @PutMapping("/{id}")
    void updateCompany(@PathVariable Integer id, @RequestBody Company company) {
        try {
            service.getById(id);
            company.setId(id);
            service.save(company);

        } catch (NoSuchElementException e) {

        }
    }
    @PostMapping("")
    void addCompany(@RequestBody Company company) {
        try {
            service.save(company);
        } catch (HttpClientErrorException.BadRequest e) {
        }
    }
    @DeleteMapping("/{id}")
    void deleteCompany(@PathVariable Integer id) {
        try {
            service.getById(id);
            service.delete(id);
        } catch (NoSuchElementException e) {
        }
    }
    //cтв ред вид
//    @PutMapping("/{id}")
//    ResponseEntity<?> updateCompany(@PathVariable Integer id, @RequestBody Company company) {
//        try {
//            service.getById(id);
//            company.setId(id);
//            service.save(company);
//            return new ResponseEntity<>(HttpStatus.OK);
//        } catch (NoSuchElementException e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }
//    @PostMapping("")
//    ResponseEntity<?> addCompany(@RequestBody Company company) {
//        try {
//            service.save(company);
//            return new ResponseEntity<>(HttpStatus.OK);
//        } catch (HttpClientErrorException.BadRequest e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }
//    @DeleteMapping("/{id}")
//    ResponseEntity<?> deleteCompany(@PathVariable Integer id) {
//        try {
//            service.getById(id);
//            service.delete(id);
//            return new ResponseEntity<>(HttpStatus.OK);
//        } catch (NoSuchElementException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

}
