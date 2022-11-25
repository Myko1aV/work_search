package com.architecture.ahfi.services.implementation;

import com.architecture.ahfi.entities.Key;
import com.architecture.ahfi.entities.Response;
import com.architecture.ahfi.entities.User;
import com.architecture.ahfi.repositories.ResponseRepository;
import com.architecture.ahfi.repositories.UserRepository;
import com.architecture.ahfi.services.UserService;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    final
    UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User getUser(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException());
    }

    @Override
    public List<User> getAll() {
        return (List<User>) repository.findAll();
    }

    @Override
    public void save(User user) {
        repository.save(user);
    }

    @Override
    public void delete(Integer id) {
        getUser(id);
        repository.deleteById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return repository.getUserByEmail(email).orElseThrow(() -> new NoSuchElementException());
    }

    @Override
    public Set<String> getUserKeys(Integer id) {
        return Arrays.stream(getUser(id).getKeys().split(",")).collect(Collectors.toSet());
    }

//ABOUT THE CLIENT Founded in 1996, our customer is a German digital job marketplace for specialists and executives. The companies of all sizes and industries in need can find first-class employees through the marketplace’s web app and online services.  Our customer’s team consists of 200 people. As a subsidiary ofMedienUnion, Ludwigshafen, it has been working with renowned publishers for many years. Advertisements placed with the customer’s applications are published free of charge on the job markets of more than 400 partners’ platforms as part of a target group concept for the advertiser. Java
}
