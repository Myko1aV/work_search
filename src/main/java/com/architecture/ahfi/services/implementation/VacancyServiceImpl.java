package com.architecture.ahfi.services.implementation;

import com.architecture.ahfi.Patterns.Facade.FilterFacade;
import com.architecture.ahfi.Patterns.State;


import com.architecture.ahfi.entities.Key;
import com.architecture.ahfi.entities.Vacancy;
import com.architecture.ahfi.repositories.VacancyRepository;
import com.architecture.ahfi.services.CompanyService;
import com.architecture.ahfi.services.KeyService;
import com.architecture.ahfi.services.UserService;
import com.architecture.ahfi.services.VacancyService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
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
    final KeyService keyRepository;

    public VacancyServiceImpl(VacancyRepository repository, UserService userService, CompanyService companyService, KeyService keyRepository) {
        this.repository = repository;
        this.userService = userService;
        this.companyService = companyService;
        this.keyRepository = keyRepository;
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
    public List<Vacancy> filter(List<Object> filters, Integer userId) {
        List<Vacancy> result = new ArrayList<>();
        if(userId!=null) result = showСompatible(userId);
        else result = getAll();
        result = filters.get(0) == null ? result : result.stream().filter(x -> x.getTitle().contains(filters.get(0).toString())).collect(Collectors.toList());
        result = filters.get(1) == null ? result : result.stream().filter(x -> x.getExperience() <= (Integer) filters.get(1)).collect(Collectors.toList());
        result = filters.get(2) == null ? result : result.stream().filter(x -> x.getCity().contains(filters.get(2).toString())).collect(Collectors.toList());
        result = filters.get(3) == null ? result : result.stream().filter(x -> x.getCategoryID().getId() == filters.get(3)).collect(Collectors.toList());
        result = filters.get(4) == null ? result : result.stream().filter(x -> x.getSalary() >= (Integer) filters.get(4)).collect(Collectors.toList());
        result = filters.get(5) == null ? result : sort(result, (Integer) filters.get(5));
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
        List<String> userKeys = new ArrayList<>(userService.getUserKeys(userId));
        List<Key> keys = (List<Key>) keyRepository.getAll();
        List<Vacancy> vacancies = getAll();
        Map<Integer, Integer> vacancyPopularity = new TreeMap<>();
        for (int i = 0; i < vacancies.size(); i++) {
            Integer value = 0;
            for (int j = 0; j < userKeys.size(); j++) {
                if (vacancies.get(i).getDescription().toLowerCase().contains(userKeys.get(j).toLowerCase())) {
                    value += keyRepository.getValueByName(userKeys.get(j));
                }
            }
            vacancyPopularity.put(vacancies.get(i).getId(), value);
        }
        LinkedHashMap<Integer, Integer> sortedMap = vacancyPopularity.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder() /* Optional: Comparator.reverseOrder() */))
                .collect(Collectors.toMap(Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));
        sortedMap.values().removeIf((x) -> x <= 0);
        List<Vacancy> result = new ArrayList<>();
        for (Integer id : sortedMap.keySet()) {
            result.add(getOne(id));
        }
        return result;
    }


}
