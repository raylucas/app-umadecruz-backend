package com.umadecruz.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="APP_VALORES")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppValores {

    @Id
    @Column(name = "CHAVE")
    private String chave;

    @Column(name = "VALOR")
    private String valor;
}
