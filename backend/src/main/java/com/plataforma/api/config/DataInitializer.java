package com.plataforma.api.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.plataforma.api.model.Acompanhante;
import com.plataforma.api.model.Servico;
import com.plataforma.api.repository.AcompanhanteRepository;
import com.plataforma.api.repository.ServicoRepository;

@Configuration
@Profile("test") // ← Só executa no profile "test"
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(AcompanhanteRepository repository, BCryptPasswordEncoder passwordEncoder) {
        return args -> {
            if (repository.count() == 0) {
                // Usuário 1: Julia (com imagem de exemplo)
                Acompanhante julia = new Acompanhante();
                julia.setNome("Julia Crystal");
                julia.setEmail("jogodorizai@gmail.com");
                julia.setSenha(passwordEncoder.encode("Premium123!")); // ✅ Criptografado
                julia.setImagemUrl("https://i.imgur.com/placeholder-julia.jpg");
                repository.save(julia);

                // Usuário 2: Mark
                Acompanhante mark = new Acompanhante();
                mark.setNome("Mark Admin");
                mark.setEmail("rizaifesta@gmail.com");
                mark.setSenha(passwordEncoder.encode("Admin789#")); // ✅ Criptografado
                mark.setImagemUrl("https://i.imgur.com/placeholder-mark.jpg");
                repository.save(mark);

                System.out.println("✓ Base de dados populada com usuários de teste (dev profile).");
            }
        };
    }
    
    @Bean
    CommandLineRunner initDatabase(ServicoRepository servicoRepository) {
        return args -> {
            if (servicoRepository.count() == 0) {
                String[] servicos = {
                    "Social Escort",
                    "Dinner Date",
                    "Travel Companion",
                    "Sensual Massage",
                    "Photo Shoot"
                };
                
                for (String nome : servicos) {
                    servicoRepository.save(new Servico(nome));
                }
                System.out.println("✓ " + servicos.length + " serviços cadastrados");
            }
        };
    }
}