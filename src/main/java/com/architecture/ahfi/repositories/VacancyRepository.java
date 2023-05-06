package com.architecture.ahfi.repositories;


import com.architecture.ahfi.entities.Vacancy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VacancyRepository extends CrudRepository<Vacancy, Integer> {
    @Query("select v from Vacancy v where v.companyID.id = :companyId")
    List<Vacancy> getAllByCompanyID(@Param("companyId") Integer companyId);

    @Query("select v from Vacancy v where v.status = :status")
    List<Vacancy> getVacanciesByStatus(@Param("status") Boolean status);
}