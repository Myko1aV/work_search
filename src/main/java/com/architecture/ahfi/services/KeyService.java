package com.architecture.ahfi.services;

import com.architecture.ahfi.entities.Key;

import java.util.List;

public interface KeyService {
   Integer getKeyValueById(Integer id);

   Integer getValueByName(String name);
   List<Key> getAll();
}