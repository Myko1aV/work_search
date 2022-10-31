package com.architecture.ahfi.services;

import com.architecture.ahfi.entities.Key;
import com.architecture.ahfi.entities.User;

import java.util.Set;

public interface UserService {
    User getUser(Integer id);
    Set<Key> getUserKeys(Integer id);
}
