package com.plataforma.api.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "acompanhantes")
@Data // O Lombok gera getters e setters automaticamente
public class Acompanhante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    private String senha;
    
    @Column(name = "imagem_url")
    private String imagemUrl;
    
    @Column(columnDefinition = "TEXT")
    private String biografia;
    
    private BigDecimal saldoCreditos = BigDecimal.ZERO;
    private boolean perfilVisivel = false;

    // Localização (Para o raio de busca no Reino Unido)
    private Double latitude;
    private Double longitude;
    private String cidade;

    // Relacionamento com a Tabela de Serviços
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "acompanhante_servicos",
        joinColumns = @JoinColumn(name = "acompanhante_id"),
        inverseJoinColumns = @JoinColumn(name = "servico_id")
    )
    private List<Servico> servicos = new ArrayList<>();
}