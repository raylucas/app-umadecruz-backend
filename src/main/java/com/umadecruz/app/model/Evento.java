package com.umadecruz.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="EVENTO")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  id;

    @Column(name = "TITULO")
    private String titulo;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "DATA")
    private LocalDate data;

    @Column(name = "INICIO")
    private LocalTime inicio;

    @Column(name = "FIM")
    private LocalTime fim;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO_CRIACAO")
    private Usuario usuario;

    @Column(name = "DATA_CRIACAO")
    private LocalDate dataCriacao;


}
