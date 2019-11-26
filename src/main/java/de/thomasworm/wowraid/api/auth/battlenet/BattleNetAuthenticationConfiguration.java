package de.thomasworm.wowraid.api.auth.battlenet;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration()
class BattleNetAuthenticationConfiguration {

    @Bean(name = "spring.security.oauth2.client.registration.battlenet.scope")
    @ConfigurationProperties(prefix = "spring.security.oauth2.client.registration.battlenet.scope")
    public List<String> getConfigurationPropertySpringSecurityOauth2ClientRegistrationBattlenetScope() {
        return new ArrayList<String>();
    }

}