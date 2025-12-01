package com.umadecruz.app.repository;

import com.umadecruz.app.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Integer> {
}
