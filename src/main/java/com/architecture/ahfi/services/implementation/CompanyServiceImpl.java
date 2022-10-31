package com.architecture.ahfi.services.implementation;

import com.architecture.ahfi.entities.Company;
import com.architecture.ahfi.repositories.CompanyRepository;
import com.architecture.ahfi.services.CompanyService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    CompanyRepository repository;
    @Override
    public Company getById(Integer id) {
        return repository.findById(id).orElseThrow(()-> new NoSuchElementException("No such element exception"));
    }

    @Override
    public List<Company> getAll() {
        return (List<Company>) repository.findAll();
    }
}
