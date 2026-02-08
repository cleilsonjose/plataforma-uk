package com.plataforma.api.repository;

import com.plataforma.api.model.Acompanhante;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface AcompanhanteRepository extends JpaRepository<Acompanhante, Long> {
    // É uma Interface porque o Spring vai "escrever" o SQL para nós.
	
	Optional<Acompanhante> findByEmail(String email);
	
}