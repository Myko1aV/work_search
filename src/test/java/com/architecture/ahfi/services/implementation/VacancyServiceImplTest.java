package com.architecture.ahfi.services.implementation;

import com.architecture.ahfi.entities.Category;
import com.architecture.ahfi.entities.Company;
import com.architecture.ahfi.entities.Vacancy;
import com.architecture.ahfi.services.CategoryService;
import com.architecture.ahfi.services.CompanyService;
import com.architecture.ahfi.services.VacancyService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VacancyServiceImplTest {

    @Autowired
    VacancyService service;
    @Autowired
    CategoryService categoryService;
    @Autowired
    CompanyService companyService;

    static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(10),
                Arguments.of(2),
                Arguments.of(1),
                Arguments.of(0)


        );
    }
    static Stream<Arguments> testCases2() {
        return Stream.of(
                Arguments.of(10, 0),
                Arguments.of(2,2),
                Arguments.of(1,5),
                Arguments.of(0,0)
        );
    }
    public static Stream<Arguments> testCases1() {

        return Stream.of(
                Arguments.of(1, Arrays.asList(new Vacancy(1, 1000, LocalDate.of(2022, 5, 22)), new Vacancy(2, 10003, LocalDate.of(2020, 5, 22))), Arrays.asList(new Vacancy(1, 1000, LocalDate.of(2022, 5, 22)), new Vacancy(2, 10003, LocalDate.of(2020, 5, 22)))),
                Arguments.of(2, Arrays.asList(new Vacancy(1, 1000, LocalDate.of(2022, 5, 22)), new Vacancy(2, 10003, LocalDate.of(2020, 5, 22))), Arrays.asList(new Vacancy(2, 10003, LocalDate.of(2020, 5, 22)), new Vacancy(1, 1000, LocalDate.of(2022, 5, 22)))),
                Arguments.of(3, Arrays.asList(new Vacancy(1, 1000, LocalDate.of(2022, 5, 22)), new Vacancy(2, 10003, LocalDate.of(2020, 5, 22))), Arrays.asList(new Vacancy(2, 10003, LocalDate.of(2020, 5, 22)), new Vacancy(1, 1000, LocalDate.of(2022, 5, 22)))),
                Arguments.of(4, Arrays.asList(new Vacancy(1, 1000, LocalDate.of(2022, 5, 22)), new Vacancy(2, 10003, LocalDate.of(2020, 5, 22))), Arrays.asList(new Vacancy(1, 1000, LocalDate.of(2022, 5, 22)), new Vacancy(2, 10003, LocalDate.of(2020, 5, 22)))),
                Arguments.of(100, Arrays.asList(new Vacancy(1, 1000, LocalDate.of(2022, 5, 22)), new Vacancy(2, 10003, LocalDate.of(2020, 5, 22))), Arrays.asList(new Vacancy(1, 1000, LocalDate.of(2022, 5, 22)), new Vacancy(2, 10003, LocalDate.of(2020, 5, 22))))
        );
    }

    static Stream<Arguments> filterCases() {
        return Stream.of(
                Arguments.of(Arrays.asList(null, null, "Lviv", null, null, 1), Arrays.asList(new Vacancy(9, "Java job for Vitalik", false, 10000, new Company(1, "ahfi"), new Category(1, "IT"), "test", LocalDate.parse("2022-10-30"), "Lviv", 10), new Vacancy(32, "Java job for Vitalik", false, 15000, new Company(2, "test"), new Category(2, "Finance"), "test", LocalDate.parse("2020-10-15"), "Lviv", 10))),
                Arguments.of(Arrays.asList("a", 11, "Lviv", null, 11111, 1), Arrays.asList(new Vacancy(32, "Java job for Vitalik", false, 15000, new Company(2, "test"), new Category(2, "Finance"), "test", LocalDate.parse("2020-10-15"), "Lviv", 10))),
                Arguments.of(Arrays.asList(null, null, "qq", 2, null, 2), Arrays.asList()),
                Arguments.of(Arrays.asList("for", 1, null, 1, 10000, 3), Arrays.asList()),
                Arguments.of(Arrays.asList("V", null, "lv", null, 100, 9), Arrays.asList())

        );
    }

    static Stream<Arguments> saveCases() {
        return Stream.of(
                Arguments.of(new Vacancy(0, "Java job for Vitalik3", false, 10000, new Company(3, "test"), new Category(1, "IT"), "test", LocalDate.parse("2022-10-30"), "3", 10)),
                Arguments.of(new Vacancy(0, "Java job for Vitalik2", false, 10000, new Company(3, "test"), new Category(1, "IT"), "test", LocalDate.parse("2022-10-30"), "2", 10)),
                Arguments.of(new Vacancy(0, "Java job for Vitalik3", false, 10000, new Company(3, "test"), new Category(1, "IT"), "test", LocalDate.parse("2022-10-30"), "3", 10)),
                Arguments.of(new Vacancy(0, "Java job for Vitalik4", false, 10000, new Company(3, "test"), new Category(1, "IT"), "test", LocalDate.parse("2022-10-30"), "4", 10)),
                Arguments.of(new Vacancy(0, "Java job for Vitalik5", false, 10000, new Company(3, "test"), new Category(1, "IT"), "test", LocalDate.parse("2022-10-30"), "5", 10))

        );
    }

    @ParameterizedTest
    @MethodSource("testCases")
    void getOne(Integer id) {
        try {
            Vacancy vacancy = service.getOne(id);
            assertEquals(vacancy.getId(), id);
        } catch (NoSuchElementException e) {
            String msg = e.getMessage();
            assertEquals("No such element exception", msg);
        }
    }

    @MethodSource("filterCases")
    @ParameterizedTest
    void filter(List<Object> filters, List<Object> expected) {

        List<Vacancy> vacancyList = service.filter(filters, null);
        assertEquals(vacancyList.toString(), expected.toString());
    }

    @MethodSource("testCases1")
    @ParameterizedTest
    void sort(Integer sort, List<Vacancy> vacancies, List<Vacancy> expected) {
        List<Vacancy> vacancyList = service.sort(vacancies, sort);
        assertEquals(vacancyList.toString(), expected.toString());
    }

    @MethodSource("testCases")
    @ParameterizedTest
    void approve(Integer id) {
        try {
            service.approve(id);
            assertTrue(service.getOne(id).getStatus());
        } catch (NoSuchElementException e) {
            String msg = e.getMessage();
            assertEquals("No such element exception", msg);
        }
    }

    @MethodSource("testCases")
    @ParameterizedTest
    void decline(Integer id) {
        try {
            service.decline(id);
            assertFalse(service.getOne(id).getStatus());
        } catch (NoSuchElementException e) {
            String msg = e.getMessage();
            assertEquals("No such element exception", msg);
        }
    }

    @MethodSource("saveCases")
    @ParameterizedTest
    void save(Vacancy vacancy) throws InterruptedException {
        int length = service.getAll().size();
        service.save(vacancy);
        assertEquals(service.getAll().size(), length + 1);

    }

    @ParameterizedTest
    @MethodSource("testCases")
    void delete(Integer id) {
        try {
            int length = service.getAll().size();
            service.delete(id);
            assertEquals(length - 1, service.getAll().size());
        } catch (NoSuchElementException e) {
            String msg = e.getMessage();
            assertEquals("No such element exception", msg);
        }
    }

    @ParameterizedTest
    @MethodSource("testCases2")
    void getAllByCompany(Integer id , Integer expected) {
        try {
           int lenght = service.getAllByCompany(id).size();
            assertEquals(lenght,expected);
        } catch (NoSuchElementException e) {
            String msg = e.getMessage();
            assertEquals("No such element exception", msg);
        }
    }

}