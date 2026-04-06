package springboot.samples.different.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  private static final String[] WHITELIST = {
      "/statemachine/cdplayer",
      "/statemachine/persist",
      "/statemachine/scope",
      "/statemachine/showcase",
      "/statemachine/tasks",
      "/statemachine/turnstile",
      "/statemachine/washer",
      "/statemachine/zookeeper"
      //note: not added /statemachine/security because it is secured.
  };

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(auth -> auth
        .requestMatchers(WHITELIST).permitAll()                          // These are unsecured
        .anyRequest()
        .authenticated()                                   // Everything else is secured
    );

    http.formLogin(Customizer.withDefaults()); // Enables the default /login page
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
