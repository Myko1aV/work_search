package com.architecture.ahfi.services.implementation;

import com.architecture.ahfi.entities.Company;
import com.architecture.ahfi.repositories.CompanyRepository;
import com.architecture.ahfi.services.CompanyService;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {
    final
    CompanyRepository repository;

    public CompanyServiceImpl(CompanyRepository repository) {
        this.repository = repository;
    }

    @Override
    public Company getById(Integer id) {
        return repository.findById(id).orElseThrow(()-> new NoSuchElementException("No such element exception"));
    }

    @Override
    public List<Company> getAll() {
        return (List<Company>) repository.findAll();
    }

    @Override
    public void save(Company company) {
        repository.save(company);
    }
    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
