package de.thomasworm.wowraid.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration()
class WowraidApiConfiguration {

    @Bean()
    public SecurityWebFilterChain securitygWebFilterChain(ServerHttpSecurity http) {
        return http
            .oauth2Login()
            .and()
            .authorizeExchange()
            .pathMatchers("GET", "/user/authenticated")
            .permitAll()
            .anyExchange()
            .authenticated()
            .and()
            .build();
    }

}