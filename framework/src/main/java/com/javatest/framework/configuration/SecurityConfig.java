package com.javatest.framework.configuration;

import com.javatest.framework.commons.security.SecurityFilter;
import com.javatest.framework.commons.security.SecurityService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${security.jwt.secret}")
    private String secret;

    @Value("${spring.application.name}")
    private String serviceName;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                // 权限认证
                .antMatchers("/actuator/health", "/actuator/info",
                        "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html", "/webui.html",
                        "/api-docs/**", "/api-docs.yaml").permitAll()
                // 其他部分不受限
                .anyRequest().authenticated()
                .and()
                // 增加filter
                .addFilterBefore(new SecurityFilter(new SecurityService(secret, serviceName)),
                        UsernamePasswordAuthenticationFilter.class);

    }


}

