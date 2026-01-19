package com.plataforma.api.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "acompanhantes")
@Data // O Lombok gera getters e setters automaticamente
public class Acompanhante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String senha;
    
    @Column(columnDefinition = "TEXT")
    private String biografia;
    
    private BigDecimal saldoCreditos = BigDecimal.ZERO;
    private boolean perfilVisivel = false;

    // Localização (Para o raio de busca)
    private Double latitude;
    private Double longitude;
    private String cidade;
}