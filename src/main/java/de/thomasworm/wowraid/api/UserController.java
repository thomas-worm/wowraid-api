package de.thomasworm.wowraid.api;

import de.thomasworm.wowraid.api.model.dto.UserInfo;
import de.thomasworm.wowraid.api.model.persistence.User;

import java.security.Principal;
import java.util.Arrays;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController()
class UserController {

    private UserService userService;

    @Autowired()
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<OAuth2AuthenticationToken> hello(OAuth2AuthenticationToken currentUser)  {
        return ResponseEntity.ok(currentUser);
    }

    @GetMapping("/user")
    public Mono<UserInfo> getUser(OAuth2AuthenticationToken token) {
        return Mono.just(getUserInfoFromToken(token));
    }

    @GetMapping("/user/login")
    public Mono<Void> loginUser(@RequestParam("redirect_uri") String redirectUri, ServerWebExchange exchange, OAuth2AuthenticationToken token) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.FOUND);
        response.getHeaders().put(HttpHeaders.LOCATION, Arrays.asList(new String[] {redirectUri}));
        getUserInfoFromToken(token);
        return response.setComplete();
    }

    private UserInfo getUserInfoFromToken(OAuth2AuthenticationToken token) {
        User user = this.userService.getUserByToken(token);
        UserInfo userInfo = new UserInfo();
        userInfo.setUserIdentifier(user.getBlizzardIdentifier());
        userInfo.setBattleTag(user.getBattleTag());
        Set<String> groups = userInfo.getGroups();
        user.getGroups().forEach(group -> {
            String groupName = group.getName();
            if (!groups.contains(groupName)) {
                groups.add(groupName);
            }
        });
        userInfo.setAuthenticated(true);
        return userInfo;
    }

    @GetMapping("/user/authenticated")
    public Mono<Boolean> isAuthenticated(final Principal principal) {
        return Mono.just(principal != null);
    }

}
