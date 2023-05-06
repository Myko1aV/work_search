package com.architecture.ahfi.repositories;

import com.architecture.ahfi.entities.Key;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface KeyRepository extends CrudRepository<Key, Integer> {
    @Query("select k from Key k where k.name = :name")
    Key getKeyByName(@Param("name") String name);
}