package com.umadecruz.app.repository;

import com.umadecruz.app.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Modifying
    @Query("DELETE FROM Token t WHERE t.token = :token")
    void deleteByToken(String token);


}