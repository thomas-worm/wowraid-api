package de.thomasworm.wowraid.api;

import de.thomasworm.wowraid.api.model.UserInfo;

import java.security.Principal;
import java.util.Arrays;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@RestController
class UserController {

    @GetMapping("/me")
    public ResponseEntity<OAuth2AuthenticationToken> hello(OAuth2AuthenticationToken currentUser)  {
        return ResponseEntity.ok(currentUser);
    }

    @GetMapping("/user")
    public Mono<UserInfo> getUser(OAuth2AuthenticationToken token) {
        return Mono.just(new UserInfo(token));
    }

    @GetMapping("/user/login")
    public Mono<Void> loginUser(@RequestParam("redirect_uri") String redirectUri, ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.FOUND);
        response.getHeaders().put(HttpHeaders.LOCATION, Arrays.asList(new String[] {redirectUri}));
        return response.setComplete();
    }

    @GetMapping("/user/authenticated")
    public Mono<Boolean> isAuthenticated(final Principal principal) {
        return Mono.just(principal != null);
    }

}
