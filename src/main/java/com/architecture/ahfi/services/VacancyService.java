package com.architecture.ahfi.services;

import com.architecture.ahfi.Patterns.State;
import com.architecture.ahfi.entities.Response;
import com.architecture.ahfi.entities.Vacancy;

import java.util.List;

public interface VacancyService {
    Vacancy getOne(Integer id);

    List<Vacancy> getAll();

    List<Vacancy> filter(List<Object> filters, String type);

    List<Vacancy> sort(List<Vacancy> vacancies, Integer parameter);

    void approve(Integer id);

    void decline(Integer id);

    void save(Vacancy vacancy);

    void delete(Integer vacancyId);

    List<Vacancy> getAllByCompany(Integer companyId);

    List<Vacancy> showСompatible(Integer userId);
}
