package com.architecture.ahfi.services.implementation;

import com.architecture.ahfi.entities.Key;
import com.architecture.ahfi.entities.User;
import com.architecture.ahfi.repositories.UserRepository;
import com.architecture.ahfi.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository repository;
    @Override
    public User getUser(Integer id) {
        return repository.findById(id).orElseThrow(()-> new NoSuchElementException());
    }

    @Override
    public Set<Key> getUserKeys(Integer id) {
        return getUser(id).getKeys();
    }
}
