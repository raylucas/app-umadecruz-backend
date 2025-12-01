package com.umadecruz.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="AVISO")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Aviso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  id;

    @Column(name = "TITULO")
    private String titulo;

    @Column(name = "CORPO")
    private String corpo;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO_CRIACAO")
    private Usuario usuario;

    @Column(name = "DATA_CRIACAO")
    private LocalDate dataCriacao;

}
