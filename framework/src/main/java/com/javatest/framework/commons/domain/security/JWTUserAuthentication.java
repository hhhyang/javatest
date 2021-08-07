package com.javatest.framework.commons.domain.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.stream.Collectors;

public class JWTUserAuthentication implements Authentication {

    private JWTUser jwtUser;

    private boolean authentication = false;

    public JWTUserAuthentication(JWTUser jwtUser, boolean authentication)  {
        this.jwtUser = jwtUser;
        this.authentication = authentication;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return jwtUser.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public Object getCredentials() {
        return "";
    }

    @Override
    public Object getDetails() {
        return jwtUser;
    }

    @Override
    public Object getPrincipal() {
        return jwtUser.getUser();
    }

    @Override
    public boolean isAuthenticated() {
        return authentication;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {
        this.authentication = b;
    }

    @Override
    public String getName() {
        return jwtUser.getUser();
    }
}
