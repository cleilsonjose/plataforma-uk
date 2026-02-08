package com.plataforma.api.service;

import com.plataforma.api.model.Acompanhante;
import com.plataforma.api.repository.AcompanhanteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AcompanhanteService {

    private final AcompanhanteRepository repository;

    public AcompanhanteService(AcompanhanteRepository repository) {
        this.repository = repository;
    }

    public Acompanhante obterOuCriarPorEmailSocial(String email, String nome, String imageUrl) {
        Optional<Acompanhante> acompanhanteExiste = repository.findByEmail(email);

        if (acompanhanteExiste.isEmpty()) {
            return criarNovoAcompanhante(email, nome, imageUrl);
        }

        Acompanhante existente = acompanhanteExiste.get();
        
        // Só atualiza nome se ainda NÃO existir
        if (existente.getNome() == null && nome != null) {
            existente.setNome(nome);
        }
        
        // Só atualiza imagem se ainda NÃO existir
        if (existente.getImagemUrl() == null && imageUrl != null) {
            existente.setImagemUrl(imageUrl);
        }
        
        return repository.save(existente);
    }

    private Acompanhante criarNovoAcompanhante(String email, String nome, String imageUrl) {
        Acompanhante novo = new Acompanhante();
        novo.setEmail(email);
        novo.setNome(nome);
        novo.setImagemUrl(imageUrl);
        novo.setSenha("OAUTH2_USER_" + System.currentTimeMillis());
        return repository.save(novo);
    }
}