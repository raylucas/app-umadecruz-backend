package com.umadecruz.app.service;

import com.umadecruz.app.model.AppUser;
import com.umadecruz.app.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepository repository;

    public Optional<AppUser> buscarPorEmail(String email){
        return repository.findByEmail(email);
    }

    public AppUser salvar(AppUser appUser){
        return repository.save(appUser);
    }

}
