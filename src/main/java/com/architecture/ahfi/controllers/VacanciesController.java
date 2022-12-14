package com.architecture.ahfi.controllers;

import com.architecture.ahfi.entities.Company;
import com.architecture.ahfi.entities.Vacancy;
import com.architecture.ahfi.entities.Vacancy;
import com.architecture.ahfi.repositories.VacancyRepository;
import com.architecture.ahfi.services.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/vacancy")
public class VacanciesController {
    @Autowired
    VacancyService service;
    @GetMapping("")
    List<Vacancy> getAll() {
        return (List<Vacancy>) service.getAll();
    }
    @GetMapping("/{id}")
    ResponseEntity<Vacancy> getOne(@PathVariable Integer id) {
        try {
            Vacancy vacancy = service.getOne(id);
            return new ResponseEntity<>(vacancy, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{id}")
    ResponseEntity<?> updateVacancy(@PathVariable Integer id, @RequestBody Vacancy vacancy) {
        try {
            service.getOne(id);
            vacancy.setId(id);
            service.save(vacancy);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("")
    ResponseEntity<?> addVacancy(@RequestBody Vacancy vacancy) {
        try {
            service.save(vacancy);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (HttpClientErrorException.BadRequest e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteVacancy(@PathVariable Integer id) {
        try {
            service.getOne(id);
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/decline")
    ResponseEntity<?> decline(@PathVariable Integer id) {
        try {
            service.decline(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/approve")
    ResponseEntity<?> approve(@PathVariable Integer id) {
        try {
            service.approve(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/filter")
    List<Vacancy> getFilteredVacancies(@RequestParam( value = "title", required = false) String title ,@RequestParam( value = "experience", required = false) Integer experience, @RequestParam( value = "city", required = false) String city, @RequestParam(value = "categoryID", required = false) Integer categoryId, @RequestParam(value = "salary", required = false) Integer salary , @RequestParam(value = "sort", required = true)  Integer sort) {
       List<Object> filters = Arrays.asList(title, experience, city, categoryId, salary, sort);
        System.out.println(filters);
        return service.filter(filters);
    }

    @GetMapping("/user")
    List<Vacancy> getPersonalVacancies(@RequestParam("userId") Integer userId) {
        return service.show??ompatible(userId);
    }

    @GetMapping("/company")
    List<Vacancy> getCompanyVacancies(@RequestParam("companyId") Integer companyId) {
        return service.getAllByCompany(companyId);
    }
}
