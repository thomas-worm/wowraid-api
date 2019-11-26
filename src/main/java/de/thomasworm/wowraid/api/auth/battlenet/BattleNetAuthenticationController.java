package de.thomasworm.wowraid.api.auth.battlenet;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import reactor.core.publisher.Mono;

@RestController()
class BattleNetAuthenticationController {

    private BattleNetAuthenticationService authenticationService;

    @Autowired()
    public BattleNetAuthenticationController(BattleNetAuthenticationService battleNetAuthenticationService) {
        this.authenticationService = battleNetAuthenticationService;
    }

    @GetMapping("/oauth2/authorization/battlenet")
    public Mono<Void> authenticate(ServerHttpResponse response, ServerHttpRequest request) {
        return redirect(response, authenticationService.createAuthorizationUri(getRedirectUrl(request)));
    }

    @GetMapping("/login/oauth2/code/battlenet")
    public Mono<String> authenticateCallback(@RequestParam("code") String code, @RequestParam("state") Optional<String> state) {
        return authenticationService.getToken(Mono.just(code));
    }

    private Mono<Void> redirect(ServerHttpResponse response, URI uri) {
        response.setStatusCode(HttpStatus.TEMPORARY_REDIRECT);
        response.getHeaders().setLocation(uri);
        return response.setComplete();
    }

    private URI getRedirectUrl(ServerHttpRequest request) {
        return UriComponentsBuilder
            .fromUri(request.getURI())
            .replacePath("/login/oauth2/code/battlenet")
            .build().toUri();    
    }

}