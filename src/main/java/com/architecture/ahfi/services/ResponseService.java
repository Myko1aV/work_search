package com.architecture.ahfi.services;

import com.architecture.ahfi.entities.Response;
import com.architecture.ahfi.entities.User;

import java.util.List;

public interface ResponseService {
    Response getOne(Integer id);
    List<Response> getAll();

    Response save(Response response);
    List<Response> getAllByUser(Integer userId);
    List<Response> getAllByVacancy(Integer vacancyId);
    void delete(Integer id);
}
