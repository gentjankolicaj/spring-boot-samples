package springboot.samples.jwt02.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import springboot.samples.jwt02.security.filter.CustomAuthenticationFilter;
import springboot.samples.jwt02.security.filter.CustomAuthorizationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor //to create constructor for final fields
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final UserDetailsService userDetailsService;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;


  //Use builder to build authentication manager
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(
        authenticationManagerBean());
    //Update filter process url because is inherited from UsernamePasswordAuthenticationFilter
    customAuthenticationFilter.setFilterProcessesUrl("/api/v1/login");

    //Disable csrf
    http.csrf().disable();

    //Make session stateless
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    //Unauthenticated API
    //Allow token refresh without authentication
    //Login & refresh token requests must not pass through authorization filter.
    //Passing through authorization filter will cause to fail because they are not authenticated
    http.authorizeRequests().antMatchers("/api/v1/login").permitAll();
    http.authorizeRequests().antMatchers("/api/v1/token/refresh/**").permitAll();

    //Authenticated & authorized  API
    //Some request authorization config
    http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/v1/user/**").hasAnyAuthority("ANON");
    http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/v1/user/**")
        .hasAnyAuthority("ADMIN");

    //All other request must be authenticated
    http.authorizeRequests().anyRequest().authenticated();

    //Create instance of filter & add filter to http
    http.addFilter(customAuthenticationFilter);

    //Add filter before other filters
    //We need to intercept any request before other filters
    http.addFilterBefore(new CustomAuthorizationFilter(),
        UsernamePasswordAuthenticationFilter.class);

  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }
}
