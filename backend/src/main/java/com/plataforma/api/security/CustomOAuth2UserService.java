package com.plataforma.api.security;

import com.plataforma.api.model.Acompanhante;
import com.plataforma.api.service.AcompanhanteService;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final AcompanhanteService acompanhanteService;

    public CustomOAuth2UserService(AcompanhanteService acompanhanteService) {
        this.acompanhanteService = acompanhanteService;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        
        String email = oAuth2User.getAttribute("email");
        String nome = oAuth2User.getAttribute("name");
        String imageUrl = oAuth2User.getAttribute("picture"); // ✅ Imagem do Google

        Acompanhante acompanhante = acompanhanteService.obterOuCriarPorEmailSocial(email, nome, imageUrl);
        
        return new CustomOAuth2User(oAuth2User, acompanhante);
    }
}