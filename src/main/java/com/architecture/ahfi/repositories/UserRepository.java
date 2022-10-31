package com.architecture.ahfi.repositories;

import com.architecture.ahfi.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}