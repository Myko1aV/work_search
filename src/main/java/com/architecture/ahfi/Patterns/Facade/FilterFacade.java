package com.architecture.ahfi.Patterns.Facade;

import com.architecture.ahfi.entities.Vacancy;

import java.util.List;

public class FilterFacade {
    private Sorting sortingVacancy ;
    private Filtering filteringClass;
    private Type type;

    public FilterFacade(Sorting sorting, Filtering filtering, Type type) {
        this.sortingVacancy = sorting;
        this.filteringClass = filtering;
        this.type = type;
    }

    public List<Vacancy> filter(List<Object> filters, String typeVacancy) {


        List<Vacancy> vacancies = type.chooseType(typeVacancy);
        vacancies = filteringClass.filter(filters, vacancies);
        vacancies = sortingVacancy.showSorted(vacancies, (Integer) filters.get(5));


        return vacancies;
    }
}
