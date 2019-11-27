package de.thomasworm.wowraid.api;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
class HelloController {

    @GetMapping("/me")
    public ResponseEntity<OAuth2AuthenticationToken> hello(OAuth2AuthenticationToken currentUser)  {
        return ResponseEntity.ok(currentUser);
    }

    @GetMapping("/user/authenticated")
    public Mono<Boolean> isAuthenticated(final Principal principal) {
        return Mono.just(principal != null);
    }

}
