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
import springboot.samples.jwt02.security.filter.CustomAuthenticationFilter;


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
        CustomAuthenticationFilter customAuthenticationFilter =new CustomAuthenticationFilter(authenticationManagerBean());
        //Update filter process url because is inherited from UsernamePasswordAuthenticationFilter
        customAuthenticationFilter.setFilterProcessesUrl("/api/v1/login");


        //Disable csrf
        http.csrf().disable();

        //Make session stateless
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        //Unsecured API
        http.authorizeRequests().antMatchers("/api/v1/login").permitAll();

        //Secured API
        //Some request authorization config
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/v1/user/**").hasAnyAuthority("ANON");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/v1/user/**").hasAnyAuthority("ADMIN");

        //All other request must be authenticated
        http.authorizeRequests().anyRequest().authenticated();

        //Create instance of filter & add filter to http
        http.addFilter(customAuthenticationFilter);
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
