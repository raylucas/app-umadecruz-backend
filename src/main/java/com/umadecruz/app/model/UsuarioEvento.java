package com.umadecruz.app.model;


import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "USUARIO_EVENTO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioEvento {

    @EmbeddedId
    private UsuarioEventoId id;

    @ManyToOne
    @MapsId("idUsuario")
    @JoinColumn(name = "ID_USUARIO")
    private Usuario usuario;

    @ManyToOne
    @MapsId("idEvento")
    @JoinColumn(name = "ID_EVENTO")
    private Evento evento;

    @Column(name = "data")
    private LocalDateTime data;

}

