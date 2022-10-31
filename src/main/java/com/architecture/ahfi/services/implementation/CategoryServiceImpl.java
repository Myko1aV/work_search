package com.architecture.ahfi.services.implementation;

import com.architecture.ahfi.entities.Category;
import com.architecture.ahfi.repositories.CategoryRepository;
import com.architecture.ahfi.services.CategoryService;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository repository;

    @Override
    public Category getById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("No such element exception"));
    }

    @Override
    public List<Category> getAll() {
        return (List<Category>) repository.findAll();
    }
}
