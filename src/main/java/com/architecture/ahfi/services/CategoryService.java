package com.architecture.ahfi.services;

import com.architecture.ahfi.entities.Category;
import com.architecture.ahfi.entities.Company;

import java.util.List;

public interface CategoryService {
    Category getById(Integer id);
    List<Category> getAll();
}
