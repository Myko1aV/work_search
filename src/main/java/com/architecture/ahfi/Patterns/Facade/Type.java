package com.architecture.ahfi.Patterns.Facade;

import com.architecture.ahfi.entities.Vacancy;
import com.architecture.ahfi.repositories.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Type {
    @Autowired
    VacancyRepository repository;

    List<Vacancy> chooseType(String type){
        switch (type){
            case "user": {
                return repository.getVacanciesByStatus(true);
            }
            case "unaccepted":{
                return repository.getVacanciesByStatus(false);
            }
            default:{
                return (List<Vacancy>) repository.findAll();
            }
        }
    }
}
