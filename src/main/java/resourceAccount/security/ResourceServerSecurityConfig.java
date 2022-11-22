package resourceAccount.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ResourceServerSecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.antMatcher("/private/**")
                .authorizeRequests()
                .mvcMatchers("private/user/**").access("hasAuthority('SCOPE_ownuser.admin')")
                .anyRequest().authenticated()
                .and().csrf().disable()
                .oauth2ResourceServer()
                .jwt();
        return http.build();
    }



}
