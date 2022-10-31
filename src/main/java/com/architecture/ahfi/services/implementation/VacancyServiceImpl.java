package com.architecture.ahfi.services.implementation;

import com.architecture.ahfi.entities.Vacancy;
import com.architecture.ahfi.repositories.VacancyRepository;
import com.architecture.ahfi.services.CompanyService;
import com.architecture.ahfi.services.UserService;
import com.architecture.ahfi.services.VacancyService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
public class VacancyServiceImpl implements VacancyService {
    @Autowired
    VacancyRepository repository;
    @Autowired
    UserService userService;
    @Autowired
    CompanyService companyService;

    @Override
    public Vacancy getOne(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("No such element exception"));
    }

    @Override
    public List<Vacancy> getAll() {
        return (List<Vacancy>) repository.findAll();
    }

    @Override
    public List<Vacancy> filter(List<Object> filters) {
        List<Vacancy> vacancies = getAll();
        List<Vacancy> result = new ArrayList<>();
        result = filters.get(0) == null ? vacancies : vacancies.stream().filter(x -> x.getTitle().contains(filters.get(0).toString())).toList();
        result = filters.get(1) == null ? result : result.stream().filter(x -> x.getExperience() <= (Integer) filters.get(1)).collect(Collectors.toList());
        result = filters.get(2) == null ? result : result.stream().filter(x -> x.getCity().contains(filters.get(2).toString())).collect(Collectors.toList());
        result = filters.get(3) == null ? result : result.stream().filter(x -> x.getCategoryID().getId() == filters.get(3)).collect(Collectors.toList());
        result = filters.get(4) == null ? result : result.stream().filter(x -> x.getSalary() >= (Integer) filters.get(4)).collect(Collectors.toList());
        result = sort(result, (Integer) filters.get(5));
        return result;
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
