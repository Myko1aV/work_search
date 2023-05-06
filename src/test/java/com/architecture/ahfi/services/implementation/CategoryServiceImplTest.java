package com.architecture.ahfi.services.implementation;

import com.architecture.ahfi.entities.Category;
import com.architecture.ahfi.entities.Vacancy;
import com.architecture.ahfi.services.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryServiceImplTest {
    @Autowired
    CategoryService service;
    static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(10),
                Arguments.of(2),
                Arguments.of(1),
                Arguments.of(0)


        );
    }
    @MethodSource("testCases")
    @ParameterizedTest
    void getById(Integer id) {
        try {
            Category category = service.getById(id);
            assertEquals(category.getId(), id);
        } catch (NoSuchElementException e) {
            String msg = e.getMessage();
            assertEquals("No such element exception", msg);
        }
    }
}