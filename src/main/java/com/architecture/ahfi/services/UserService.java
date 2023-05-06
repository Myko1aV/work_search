package com.architecture.ahfi.services;

import com.architecture.ahfi.entities.Key;
import com.architecture.ahfi.entities.Response;
import com.architecture.ahfi.entities.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    User getUser(Integer id);
    List<User> getAll();

    void save(User user);
    void delete(Integer id);
    User getUserByEmail(String email);
    Set<String> getUserKeys(Integer id);
}
