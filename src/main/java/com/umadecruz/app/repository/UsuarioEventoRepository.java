package com.umadecruz.app.repository;

import com.umadecruz.app.model.Evento;
import com.umadecruz.app.model.UsuarioEvento;
import com.umadecruz.app.model.UsuarioEventoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioEventoRepository extends JpaRepository<UsuarioEvento, UsuarioEventoId> {

    Optional<UsuarioEvento> findByEventoIdAndUsuarioId(Integer idEvento , Integer idUsuario);

    @Query("SELECT ue FROM UsuarioEvento ue WHERE ue.evento.id = :idEvento")
    List<UsuarioEvento> findByEventoId(@Param("idEvento") Integer idEvento);

    @Query("SELECT ue FROM UsuarioEvento ue WHERE ue.usuario.id = :idUsuario")
    List<UsuarioEvento> findByUsuarioId(@Param("idUsuario") Integer idUsuario);

    @Query("SELECT COUNT(ue) FROM UsuarioEvento ue WHERE ue.evento.id = :idEvento")
    Integer countPresencasByEventoId(@Param("idEvento") Integer idEvento);

    @Query("SELECT COUNT(ue) FROM UsuarioEvento ue WHERE ue.usuario.id = :idUsuario")
    Integer countPresencasByUsuarioId(@Param("idUsuario") Integer idUsuario);

    @Query("SELECT ue.usuario.id, COUNT(ue) as totalPresencas " +
           "FROM UsuarioEvento ue " +
           "GROUP BY ue.usuario.id " +
           "ORDER BY totalPresencas DESC")
    List<Object[]> findUsuariosComMaiorPresenca();

    @Query("SELECT ue.evento.id, COUNT(ue) as totalPresencas " +
           "FROM UsuarioEvento ue " +
           "GROUP BY ue.evento.id " +
           "ORDER BY totalPresencas DESC")
    List<Object[]> findEventosComMaiorPresenca();

    @Query("SELECT COUNT(DISTINCT ue.usuario.id) FROM UsuarioEvento ue")
    Integer countUsuariosComPresenca();

    @Query("SELECT COUNT(DISTINCT ue.evento.id) FROM UsuarioEvento ue")
    Integer countEventosComPresenca();

    @Query("SELECT COUNT(ue) FROM UsuarioEvento ue")
    Integer countTotalPresencas();

    @Query("SELECT DISTINCT ue.evento FROM UsuarioEvento ue ORDER BY ue.evento.data DESC")
    List<Evento> findAllEventosComPresenca();

}