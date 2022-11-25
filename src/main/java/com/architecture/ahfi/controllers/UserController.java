package com.architecture.ahfi.controllers;

import com.architecture.ahfi.entities.Category;
import com.architecture.ahfi.entities.User;
import com.architecture.ahfi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    User getOne(@PathVariable Integer id) {
        return userService.getUser(id);
    }

    @GetMapping("/mail")
    User getOneByMail(@RequestParam(name = "email") String email) {
        return userService.getUserByEmail(email);
    }

    @GetMapping("")
    List<User> getAll() {
        return userService.getAll();
    }

    @PostMapping("")
    ResponseEntity<?> addUser(@RequestBody User user) {
        try {
            userService.save(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (HttpClientErrorException.BadRequest e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            userService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
