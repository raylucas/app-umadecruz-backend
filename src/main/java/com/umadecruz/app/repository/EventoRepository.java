package com.umadecruz.app.repository;

import com.umadecruz.app.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {

    List<Evento> findByDataBetween(LocalDate inicio, LocalDate fim);


}
