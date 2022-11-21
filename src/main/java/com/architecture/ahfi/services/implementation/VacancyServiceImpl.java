package com.architecture.ahfi.services.implementation;

import com.architecture.ahfi.Patterns.Facade.FilterFacade;
import com.architecture.ahfi.Patterns.Facade.Filtering;
import com.architecture.ahfi.Patterns.Facade.Sorting;
import com.architecture.ahfi.Patterns.Facade.Type;
import com.architecture.ahfi.Patterns.State;


import com.architecture.ahfi.entities.Response;
import com.architecture.ahfi.entities.Vacancy;
import com.architecture.ahfi.repositories.ResponseRepository;
import com.architecture.ahfi.repositories.VacancyRepository;
import com.architecture.ahfi.services.CompanyService;
import com.architecture.ahfi.services.UserService;
import com.architecture.ahfi.services.VacancyService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
public class VacancyServiceImpl implements VacancyService {
    private State state;
    final
    VacancyRepository repository;
    final
    UserService userService;
    final
    CompanyService companyService;
    final
    FilterFacade facade;

    public VacancyServiceImpl(VacancyRepository repository, UserService userService, CompanyService companyService) {
        this.repository = repository;
        this.userService = userService;
        this.companyService = companyService;
        this.state = null;
        this.facade = null;
    }

    @Override
    public Vacancy getOne(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("No such element exception"));
    }

    @Override
    public List<Vacancy> getAll() {
        return (List<Vacancy>) repository.findAll();
    }

    @Override
    public List<Vacancy> filter(List<Object> filters,  String type) {
       FilterFacade facade = new FilterFacade(new Sorting(),new Filtering(),new Type());
       return  facade.filter(filters, type);
    }

    @Override
    public List<Vacancy> sort(List<Vacancy> vacancies, Integer parameter) {
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

    @Override
    public void approve(Integer id) {
//        State state = new ApprovedState(getOne(id));
        Vacancy vacancy = getOne(id);
        vacancy.setStatus(true);
        save(vacancy);
    }

    @Override
    public void decline(Integer id) {
        Vacancy vacancy = getOne(id);
        vacancy.setStatus(false);
        save(vacancy);
    }

    @Override
    public void save(Vacancy vacancy) {

        repository.save(vacancy);
    }

    @Override
    public void delete(Integer vacancyId) {
        getOne(vacancyId);
        repository.deleteById(vacancyId);
    }

    @Override
    public List<Vacancy> getAllByCompany(Integer companyId) {
        companyService.getById(companyId);
        return repository.getAllByCompanyID(companyId);
    }

    @Override
    public List<Vacancy> showСompatible(Integer userId) {
        // User user = userService. ;
        return null;
    }


}
