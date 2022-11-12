package com.architecture.ahfi.Patterns.Facade;

import com.architecture.ahfi.entities.Vacancy;
import com.architecture.ahfi.repositories.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Sorting {
    @Autowired
    VacancyRepository repository;

    List<Vacancy> showSorted(List<Vacancy> vacancies, Integer parameter) {
        switch (parameter) {
            case 1: //за зарплатою (від нижчої до вищої)
                return vacancies.stream().sorted(Comparator.comparing(Vacancy::getSalary)).collect(Collectors.toList());
            case 2: // за зарплатою (від вищої до нижчої)
                return vacancies.stream().sorted(Comparator.comparing(Vacancy::getSalary).reversed()).collect(Collectors.toList());
            case 3: // за датою в зрост (старіші)
                return vacancies.stream().sorted(Comparator.comparing(Vacancy::getCreatedAt)).collect(Collectors.toList());
            case 4: //за датою в спад (новіші)
                return vacancies.stream().sorted(Comparator.comparing(Vacancy::getCreatedAt).reversed()).collect(Collectors.toList());
            default:
                return vacancies;
        }
    }
}
