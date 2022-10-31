package com.architecture.ahfi.repositories;

import com.architecture.ahfi.entities.Company;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Company, Integer> {

}