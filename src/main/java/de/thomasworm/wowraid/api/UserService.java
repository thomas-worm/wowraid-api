package de.thomasworm.wowraid.api;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import de.thomasworm.wowraid.api.model.persistence.User;
import de.thomasworm.wowraid.api.model.persistence.UserRepository;

@Service()
public class UserService {

    private UserRepository userRepository;

    @Autowired()
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByToken(OAuth2AuthenticationToken token) {
        User user = new User();
        user.setBlizzardIdentifier(Integer.parseInt(token.getPrincipal().getName()));
        user.setBattleTag(token.getPrincipal().getAttribute("battle_tag"));
        return this.userRepository.addOrUpdateByBlizzardIdentifier(user);
    }

    public boolean isUserInAnyGroupWithName(User user, Iterable<String> groups) {
        Set<String> groupStrings = new HashSet<>();
        groups.forEach(group -> {
            if (!groupStrings.contains(group)) {
                groupStrings.add(group);
            }   
        });
        return user.getGroups().stream().anyMatch(group -> groupStrings.contains(group.getName()));
    }

}