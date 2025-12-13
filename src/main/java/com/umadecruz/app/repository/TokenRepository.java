package com.umadecruz.app.repository;

import com.umadecruz.app.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    void deleteByToken(String token);


}