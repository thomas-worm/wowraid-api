package de.thomasworm.wowraid.api;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.server.session.DefaultWebSessionManager;
import org.springframework.web.server.session.WebSessionIdResolver;
import org.springframework.web.server.session.WebSessionManager;

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
            .pathMatchers("OPTIONS", "/**")
            .permitAll()
            .and()
            .authorizeExchange()
            .pathMatchers("GET", "/user/authenticated")
            .permitAll()
            .anyExchange()
            .authenticated()
            .and()
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
    public WebSessionManager webSessionManager() {
        DefaultWebSessionManager manager = new DefaultWebSessionManager();
        manager.setSessionIdResolver(webSessionIdResolver());
        return manager;
    }

    @Bean()
    public WebSessionIdResolver webSessionIdResolver() {
        CookieWebSessionIdResolver resolver = new CookieWebSessionIdResolver();
        return resolver;
    }

    @Bean()
    public DataSource dataSource() throws URISyntaxException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));

        String[] userInfo = dbUri.getUserInfo().split(":");
        String username = userInfo[0];
        String password = userInfo[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
    
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setUrl(dbUrl); 
        
        return dataSource;
    }

}