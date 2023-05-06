package com.architecture.ahfi.Patterns.Facade;

import com.architecture.ahfi.entities.Vacancy;
import com.architecture.ahfi.repositories.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Filtering {
    @Autowired
    VacancyRepository repository;

    List<Vacancy> filter(List<Object> filters , List<Vacancy> vacancies){
        List<Vacancy> result = new ArrayList<>();
        result = filters.get(0) == null ? vacancies : vacancies.stream().filter(x -> x.getTitle().contains(filters.get(0).toString())).collect(Collectors.toList());
        result = filters.get(1) == null ? result : result.stream().filter(x -> x.getExperience() <= (Integer) filters.get(1)).collect(Collectors.toList());
        result = filters.get(2) == null ? result : result.stream().filter(x -> x.getCity().contains(filters.get(2).toString())).collect(Collectors.toList());
        result = filters.get(3) == null ? result : result.stream().filter(x -> x.getCategoryID().getId() == filters.get(3)).collect(Collectors.toList());
        result = filters.get(4) == null ? result : result.stream().filter(x -> x.getSalary() >= (Integer) filters.get(4)).collect(Collectors.toList());
        return result;
    }
}
