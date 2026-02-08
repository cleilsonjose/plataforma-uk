package com.plataforma.api.security;

import com.plataforma.api.model.Acompanhante;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

public class CustomOAuth2User implements OAuth2User {

    private final OAuth2User oauth2User;
    private final Acompanhante acompanhante;

    public CustomOAuth2User(OAuth2User oauth2User, Acompanhante acompanhante) {
        this.oauth2User = oauth2User;
        this.acompanhante = acompanhante;
    }

    public Acompanhante getAcompanhante() {
        return acompanhante;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oauth2User.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return oauth2User.getAuthorities();
    }

    @Override
    public String getName() {
        return oauth2User.getAttribute("name");
    }
}