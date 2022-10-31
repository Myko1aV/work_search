package com.architecture.ahfi.controllers;


import com.architecture.ahfi.entities.Response;
import com.architecture.ahfi.services.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/Responses")
public class ResponseController {
    @Autowired
    ResponseService service;

    @GetMapping("")
    List<Response> getAll() {
        return service.getAll();
    }
    @GetMapping("/user")
    List<Response> getAllByUser(@PathVariable Integer id) {
        return service.getAllByUser(id);
    }
    @GetMapping("/vacancy")
    List<Response> getAllByVacancy(@PathVariable Integer id) {
        return service.getAllByVacancy(id);
    }
    @GetMapping("/{id}")
    ResponseEntity<Response> getOne(@PathVariable Integer id) {
        try {
            Response Response = service.getOne(id);
            return new ResponseEntity<>(Response, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @PutMapping("/{id}")
    ResponseEntity<?> updateResponse(@PathVariable Integer id, @RequestBody Response response) {
        try {
            service.getOne(id);
            response.setId(id);
            service.save(response);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("")
    ResponseEntity<?> addResponse(@RequestBody Response Response) {
        try {
            service.save(Response);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (HttpClientErrorException.BadRequest e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteResponse(@PathVariable Integer id) {
        try {
            service.getOne(id);
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
