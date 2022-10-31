package com.architecture.ahfi.repositories;

import com.architecture.ahfi.entities.Response;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResponseRepository extends CrudRepository<Response, Integer> {
    @Query("select r from Response r where r.userID = :userId")
    List<Response> getResponsesByUserID(@Param("userId") Integer userId);

    @Query("select r from Response r where r.vacancyID = :vacantionId")
    List<Response> getResponsesByVacancyID(@Param("vacantionId") Integer vacantionId);
}