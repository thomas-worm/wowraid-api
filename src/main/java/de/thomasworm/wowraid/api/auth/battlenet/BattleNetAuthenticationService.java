package de.thomasworm.wowraid.api.auth.battlenet;

import java.net.URI;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import reactor.core.publisher.Mono;

@Component()
class BattleNetAuthenticationService {

    @Value("${spring.security.oauth2.client.registration.battlenet.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.provider.battlenet.issuer-uri}")
    private String issuerUri;

    @Value("${spring.security.oauth2.client.registration.battlenet.scope}")
    private String[] scopes;

    private URI authorizationEndpoint;

    @PostConstruct()
    private void initialize() {
        getConfiguration().subscribe(config -> {
            this.authorizationEndpoint = URI.create(config.getAuthorizationEndpoint());
        });
    }

    private Mono<OpenIdConfiguration> getConfiguration() {
        String configurationUriString = UriComponentsBuilder
            .fromUriString(issuerUri)
            .path(".well-known/openid-configuration")
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
            .queryParam("response_type", "code")
            .queryParam("scope", String.join(" ", scopes))
            .queryParam("client_id", clientId)
            .queryParam("state", "dumnmy")
            .build().toUri();
    }

}