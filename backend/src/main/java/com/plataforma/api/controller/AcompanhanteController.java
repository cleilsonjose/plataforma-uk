package com.plataforma.api.controller;

import com.plataforma.api.model.Acompanhante;
import com.plataforma.api.repository.AcompanhanteRepository; // Importe a Interface
import org.springframework.beans.factory.annotation.Autowired;
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
	        // Salva a Julia (ou qualquer usuário) de verdade no banco H2
	        Acompanhante salvo = repository.save(acompanhante);
	        return ResponseEntity.ok(salvo);
	    } catch (Exception e) {
	        return ResponseEntity.status(500).body("Erro ao salvar: " + e.getMessage());
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