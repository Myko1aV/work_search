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
        return (Set<String>) Arrays.stream(getUser(id).getKeys().split(",")).distinct();
    }


}
