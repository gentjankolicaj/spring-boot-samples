package springboot.samples.different.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private static final String[] WHITELIST = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/statemachine/cdplayer",
            "/statemachine/persist",
            "/statemachine/scope",
            "/statemachine/showcase",
            "/statemachine/tasks",
            "/statemachine/turnstile",
            "/statemachine/washer",
            "/statemachine/zookeeper",
            //note: did not add /statemachine/security because it is secured.
            "/statemachine/eventservice",
            "/statemachine/turnstilejpa",
            "/statemachine/turnstilejpa/createAndPersist",
            "/statemachine/turnstilejpa/restoreAndUpdate"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(WHITELIST).permitAll()                          // These are unsecured
                .anyRequest()
                .authenticated()                                   // Everything else is secured
        );

        http.formLogin(Customizer.withDefaults()); // Enables the default /login page
        http.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
        http.csrf(AbstractHttpConfigurer::disable); // Optional: disable CSRF for simpler API testing
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("password")
                .roles("USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

}
