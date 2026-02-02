package com.plataforma.api.controller;

import com.plataforma.api.model.Acompanhante;
import com.plataforma.api.repository.AcompanhanteRepository; // Importe a Interface
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/acompanhantes")
public class AcompanhanteController {

	@Autowired
	private AcompanhanteRepository repository; // Isso conecta o controller ao banco

	@PostMapping("/register")
	public ResponseEntity<?> registrar(@RequestBody Acompanhante acompanhante) {
	    try {
	        // Encriptação de senha viria aqui no futuro
	        Acompanhante salvo = repository.save(acompanhante);
	        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
	    } catch (DataIntegrityViolationException e) {
	        // Captura o erro de unique constraint do email
	        return ResponseEntity.status(HttpStatus.CONFLICT)
	                             .body("Registration failed: This email is already in use.");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body("Server error. Please try again later.");
	    }
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Acompanhante credenciais) {
		// Procura o acompanhante pelo email
		Acompanhante acompanhante = repository.findByEmail(credenciais.getEmail());

		// Verifica se existe e se a senha bate (estamos usando texto puro por enquanto)
		if (acompanhante != null && acompanhante.getSenha().equals(credenciais.getSenha())) {
			return ResponseEntity.ok(acompanhante); // Retorna o objeto se estiver tudo certo
		}

		return ResponseEntity.status(401).body("E-mail ou senha inválidos.");
	}
}