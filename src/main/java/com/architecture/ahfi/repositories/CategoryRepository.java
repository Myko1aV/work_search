package com.architecture.ahfi.repositories;

import com.architecture.ahfi.entities.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
}