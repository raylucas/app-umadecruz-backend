package com.umadecruz.app.service;

import com.umadecruz.app.model.DeviceToken;
import com.umadecruz.app.repository.DeviceTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceTokenService {

    @Autowired
    private DeviceTokenRepository repository;

    public List<DeviceToken> carregarTodos(){
        return repository.findAll();
    }

}
