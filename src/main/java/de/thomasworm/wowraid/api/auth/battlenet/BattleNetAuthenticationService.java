package de.thomasworm.wowraid.api.auth.battlenet;

import java.net.URI;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import reactor.core.publisher.Mono;

@Component()
class BattleNetAuthenticationService {

    @Value("${spring.security.oauth2.client.registration.battlenet.client-id}")
    private String clientId;
    
    @Value("${spring.security.oauth2.client.registration.battlenet.client-secret}")
    private String clientSecret;

    @Value("${spring.security.oauth2.client.provider.battlenet.issuer-uri}")
    private String issuerUri;

    @Autowired()
    @Qualifier("spring.security.oauth2.client.registration.battlenet.scope")
    private List<String> scopes;

    private URI authorizationEndpoint;
    private URI tokenEndpoint;

    @PostConstruct()
    private void initialize() {
        getConfiguration().subscribe(config -> {
            this.authorizationEndpoint = URI.create(config.getAuthorizationEndpoint());
            this.tokenEndpoint = URI.create(config.getTokenEndpoint());
        });
    }

    private Mono<OpenIdConfiguration> getConfiguration() {
        String configurationUriString = UriComponentsBuilder
            .fromUriString(issuerUri)
            .pathSegment(".well-known", "openid-configuration")
            .build().toString();

        return WebClient
            .create(configurationUriString)
            .get()
            .retrieve()
            .bodyToMono(OpenIdConfiguration.class);
    }

    public URI createAuthorizationUri(URI redirectUri) {
        return UriComponentsBuilder
            .fromUri(authorizationEndpoint)
            .queryParam("response_type", "code id_token")
            .queryParam("scope", String.join(" ", scopes.toArray(new String[scopes.size()])))
            .queryParam("client_id", clientId)
            .queryParam("state", "dumnmy")
            .queryParam("redirect_uri", redirectUri.toString())
            .build().toUri();
    }

    public Mono<String> getToken(Mono<String> authorizationCode) {
        return Mono.create(callback ->
            authorizationCode.subscribe(code ->
                WebClient
                    .create(tokenEndpoint.toString())
                    .post()
                    .header("Content-Type: application/x-www-form-urlencoded")
                    .body(
                        BodyInserters
                            .fromFormData("grant_type", "authorization_code")
                            .with("client_id", clientId)
                            .with("client_secret", clientSecret)
                            .with("code", code)
                    )
                    .retrieve().bodyToMono(String.class)
                    .subscribe(response -> 
                        callback.success(response)
                    )
            )
        );
    }

}