package com.architecture.ahfi.services.implementation;

import com.architecture.ahfi.entities.Response;
import com.architecture.ahfi.repositories.ResponseRepository;
import com.architecture.ahfi.services.ResponseService;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class ResponseServiceImpl implements ResponseService {
    final
    ResponseRepository repository;

    public ResponseServiceImpl(ResponseRepository repository) {
        this.repository = repository;
    }

    @Override
    public Response getOne(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException());
    }

    @Override
    public List<Response> getAll() {
        return (List<Response>) repository.findAll();
    }

    @Override
    public Response save(Response response) {
        return repository.save(response);
    }

    @Override
    public List<Response> getAllByUser(Integer userId) {
        return repository.getResponsesByUserID(userId);
    }

    @Override
    public List<Response> getAllByVacancy(Integer vacancyId) {
        return repository.getResponsesByVacancyID(vacancyId);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
