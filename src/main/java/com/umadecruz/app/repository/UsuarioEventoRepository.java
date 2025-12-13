package com.umadecruz.app.repository;

import com.umadecruz.app.model.UsuarioEvento;
import com.umadecruz.app.model.UsuarioEventoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioEventoRepository extends JpaRepository<UsuarioEvento, UsuarioEventoId> {

    Optional<UsuarioEvento> findByEventoIdAndUsuarioId(Integer idEvento , Integer idUsuario);

}