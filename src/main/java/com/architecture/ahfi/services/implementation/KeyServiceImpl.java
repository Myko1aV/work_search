package com.architecture.ahfi.services.implementation;

import com.architecture.ahfi.repositories.KeyRepository;
import com.architecture.ahfi.services.KeyService;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
@Service
@Transactional
public class KeyServiceImpl implements KeyService {
    final
    KeyRepository repository;

    public KeyServiceImpl(KeyRepository repository) {
        this.repository = repository;
    }

    @Override
    public Integer getKeyValueById(Integer id) {
        return repository.findById(id).orElseThrow(()-> new NoSuchElementException()).getValue();
    }
}
