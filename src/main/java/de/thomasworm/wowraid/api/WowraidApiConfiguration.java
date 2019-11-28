package de.thomasworm.wowraid.api;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.server.session.WebSessionIdResolver;

@Configuration()
class WowraidApiConfiguration {

    @Bean()
    public SecurityWebFilterChain securitygWebFilterChain(ServerHttpSecurity http) {
        return http
            .cors()
            .and()
            .oauth2Login()
            .and()
            .authorizeExchange()
            .pathMatchers("GET", "/user/authenticated")
            .permitAll()
            .anyExchange()
            .authenticated()
            .and()
            .addFilterAfter(new RemoveSameSiteFilter(), SecurityWebFiltersOrder.CSRF)
            .build();
    }

    @Bean()
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource corsConfigSource = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(Arrays.asList("https://just4fun.razorfen-wow.eu", "http://just4fun.razorfen-wow.eu"));
        corsConfig.addAllowedMethod(CorsConfiguration.ALL);
        corsConfig.setAllowCredentials(true);
        corsConfigSource.registerCorsConfiguration("/**", corsConfig);
        return corsConfigSource;
    }

    @Bean()
    public WebSessionIdResolver webSessionIdResolver() {
        CookieWebSessionIdResolver resolver = new CookieWebSessionIdResolver();
        return resolver;
    }

}