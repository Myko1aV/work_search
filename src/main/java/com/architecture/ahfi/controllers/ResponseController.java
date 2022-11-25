package com.architecture.ahfi.controllers;


import com.architecture.ahfi.entities.Response;
import com.architecture.ahfi.services.ResponseService;
import com.architecture.ahfi.services.UserService;
import com.architecture.ahfi.services.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/Responses")
public class ResponseController {
    final
    ResponseService service;

    @Autowired
    UserService userService;

    @Autowired
    VacancyService vacancyService;
    public ResponseController(ResponseService service) {
        this.service = service;
    }

    @GetMapping("")
    List<Response> getAll() {
        return service.getAll();
    }
    @GetMapping("/user/{id}")
    List<Response> getAllByUser(@PathVariable Integer id) {
        return service.getAllByUser(id);
    }
    @GetMapping("/vacancy/{id}")
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
    ResponseEntity<?> addResponse(@RequestParam(name = "userId") Integer userId , @RequestParam(name = "VacancyId") Integer vacancyId, @RequestParam(name = "file") MultipartFile file) {
        try {
            Response response = new Response(userService.getUser(userId),vacancyService.getOne(vacancyId),file.getBytes());
             service.save(response);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (HttpClientErrorException.BadRequest e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (IOException e) {
            throw new RuntimeException(e);
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
