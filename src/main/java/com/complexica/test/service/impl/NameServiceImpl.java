package com.complexica.test.service.impl;

import com.complexica.test.model.NameEntity;
import com.complexica.test.repository.NameRepository;
import com.complexica.test.service.NameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NameServiceImpl implements NameService {
    @Autowired
    private NameRepository nameRepository;


    @Override
    public List<NameEntity> findAllNames() {
        final List<NameEntity> all = nameRepository.findAll();
        return all;
    }
}
