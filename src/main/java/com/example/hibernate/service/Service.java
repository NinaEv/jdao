package com.example.hibernate.service;

import com.example.hibernate.repository.Repository;

import java.util.List;

@org.springframework.stereotype.Service
public class Service {
    private final Repository repository;

    public Service(Repository repository) {
        this.repository = repository;
    }

    public List getPersonsByCity(String city) {
        return repository.findByCity(city);
    }
}