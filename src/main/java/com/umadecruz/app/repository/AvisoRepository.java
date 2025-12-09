package com.umadecruz.app.repository;

import com.umadecruz.app.model.Aviso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AvisoRepository extends JpaRepository<Aviso, Integer> {

    List<Aviso> findByDataCriacao(LocalDate dataCriacao);

}
