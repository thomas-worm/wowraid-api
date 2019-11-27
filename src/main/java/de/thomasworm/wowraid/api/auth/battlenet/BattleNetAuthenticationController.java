package de.thomasworm.wowraid.api.auth.battlenet;

import java.io.IOException;
import java.net.URI;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

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
    public Mono<String> authenticateCallback(@RequestParam("code") String code,
            @RequestParam("state") Optional<String> state, ServerHttpRequest request) {
        return authenticationService.getToken(Mono.just(code), getRedirectUrl(request)).map(token -> {
            try {
                return authenticationService.parseIdToken(token);
            } catch (InvalidKeyException e) {
                return e.toString();
            } catch (NoSuchAlgorithmException e) {
                return e.toString();
            } catch (NoSuchPaddingException e) {
                return e.toString();
            } catch (IllegalBlockSizeException e) {
                return e.toString();
            } catch (BadPaddingException e) {
				return e.toString();
            } catch (IOException e) {
                return e.toString();
            }
        });
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