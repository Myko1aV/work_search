package com.architecture.ahfi.services;

import com.architecture.ahfi.entities.Company;

import java.util.List;

public interface CompanyService {
    Company getById(Integer id);
    List<Company> getAll();

    void save(Company company);
    void delete(Integer id);

}
