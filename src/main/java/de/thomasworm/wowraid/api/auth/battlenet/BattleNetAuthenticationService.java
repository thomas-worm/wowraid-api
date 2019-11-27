package de.thomasworm.wowraid.api.auth.battlenet;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Base64.Decoder;

import javax.annotation.PostConstruct;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import io.jsonwebtoken.Jwts;
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
    private Map<String, PublicKey> jsonWebKeySet;

    @PostConstruct()
    private void initialize() {
        getConfiguration().subscribe(config -> {
            this.authorizationEndpoint = URI.create(config.getAuthorizationEndpoint());
            this.tokenEndpoint = URI.create(config.getTokenEndpoint());
            getJsonWebKeySet(config.getJwksUri()).subscribe(keySet -> {
                this.jsonWebKeySet = keySet;
            });
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

    private Mono<Map<String, PublicKey>> getJsonWebKeySet(String jwksUrl) {
        return Mono.create(callback -> {
            Decoder base64decoder = Base64.getDecoder();
            Map<String, PublicKey> jsonWebKeySet = new HashMap<String, PublicKey>();

            WebClient
                .create(jwksUrl)
                .get()
                .retrieve()
                .bodyToMono(JsonWebKeySet.class)
                .subscribe(jwks -> {
                        for (JsonWebKey jwk : jwks.getKeys()) {
                            if (jwk.getKeyType().equals("RSA")) {
                                BigInteger modulus = new BigInteger(base64decoder.decode(jwk.getModulus()));
                                BigInteger publicExponent = new BigInteger(base64decoder.decode(jwk.getPublicExponent()));
                                try {
                                    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                                    PublicKey publicKey = keyFactory.generatePublic(new RSAPublicKeySpec(modulus, publicExponent));
                                    jsonWebKeySet.put(jwk.getKeyIdentifier(), publicKey);
                                } catch (Exception exception) {}
                            }
                        }
                        callback.success(jsonWebKeySet);
                    }
                );
        });
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

    public Mono<TokenEndpointResponse> getToken(Mono<String> authorizationCode, URI redirectUri) {
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
                    .flatMap(response -> response.bodyToMono(TokenEndpointResponse.class))
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

    public String parseIdToken(TokenEndpointResponse token) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException {
        Decoder decoder = Base64.getDecoder();

        String[] tokenParts = token.getIdToken().split("\\.", 2);
        JsonWebTokenHeader header = new ObjectMapper().readValue(decoder.decode(tokenParts[0]), JsonWebTokenHeader.class);
        PublicKey publicKey = jsonWebKeySet.get(header.getKeyIdentifier());
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        System.out.println(tokenParts[1]);
        return new String(cipher.doFinal(decoder.decode(tokenParts[1])), "UTF-8");
    }

}