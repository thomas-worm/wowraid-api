package de.thomasworm.wowraid.api.auth.battlenet;

import java.net.URI;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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
            .queryParam("redirect_uri", plainUri(redirectUri).toString())
            .build().toUri();
    }

    public Mono<String> getToken(Mono<String> authorizationCode, URI redirectUri) {
        return Mono.create(callback -> {
            authorizationCode.subscribe(code -> {
                MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
                body.add("grant_type", "authorization_code");
                body.add("client_id", clientId);
                body.add("client_secret", clientSecret);
                body.add("code", code);
                body.add("redirect_uri", plainUri(redirectUri).toString());

                WebClient
                    .create(tokenEndpoint.toString())
                    .post()
                    .bodyValue(body)
                    .exchange()
                    .flatMap(response -> response.bodyToMono(String.class))
                    .subscribe(data -> {
                        callback.success(data);
                    });
                }
            );
        });
    }

    private URI plainUri(URI uri) {
        URI newUri = null;;
        try {
            newUri = new URI(uri.getScheme(), uri.getAuthority(), uri.getPath(), null, null);
        } catch(Exception exception) {
            //
        }
        return newUri;
    }

}