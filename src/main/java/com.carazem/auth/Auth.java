package com.carazem.auth;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collection;

public class Auth implements Authentication {


    @Getter
    @Setter
    private boolean authenticated = true;
    @Getter
    @Setter
    private String email;

    public Auth(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ADMIN"));
    }

    @Override
    public Object getCredentials() {
        return email;
    }

    @Override
    public Object getDetails() {
        return email;
    }

    @Override
    public Object getPrincipal() {
        return email;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {
        this.authenticated = true;
    }

    @Override
    public String getName() {
        return email;
    }
}
