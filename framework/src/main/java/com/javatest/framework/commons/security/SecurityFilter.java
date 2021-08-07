package com.javatest.framework.commons.security;

import io.jsonwebtoken.JwtException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class SecurityFilter extends OncePerRequestFilter {

    private SecurityService securityService;

    public SecurityFilter(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            Optional<Authentication> authentication = securityService.verifyToken(httpServletRequest);
            SecurityContextHolder.getContext().setAuthentication(authentication.orElse(null));
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } catch (JwtException e ) {
            logger.info("Request from " + httpServletRequest.getRemoteUser() + " verify token failed! " + " with "
                    + "error " + e.getStackTrace());
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

    }
}
