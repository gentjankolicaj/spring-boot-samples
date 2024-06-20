package springboot.samples.jwt02.security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@RequiredArgsConstructor
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private final AuthenticationManager authenticationManager;


  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
      HttpServletResponse response) throws AuthenticationException {
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    log.info("Username : {} , password : {}", username, password);

    //Send authentication token to authentication manager
    return authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(username, password));
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain, Authentication authentication) throws IOException, ServletException {
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    Algorithm algorithm = Algorithm.HMAC256(
        "G!idSo*Y1S&q"); //Must be saved somewhere in encrypted file & loaded during startup

    String accessToken = JWT.create()
        .withSubject(
            userDetails.getUsername()) //Subject something unique about user ,example userId or username is if is unique
        .withExpiresAt(
            new Date(System.currentTimeMillis() * 10 * 60 * 1000)) // 10 minutes after expires
        .withClaim("roles",
            userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()))
        .sign(algorithm); //Sign token

    //Refresh token has longer expiry date
    String refreshToken = JWT.create()
        .withSubject(
            userDetails.getUsername()) //Subject something unique about user ,example userId or username is if is unique
        .withExpiresAt(
            new Date(System.currentTimeMillis() * 10 * 60 * 1000)) // 10 minutes after expires
        .withClaim("roles",
            userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()))
        .sign(algorithm); //Sign

    //Added tokens to http headers
    // response.setHeader("Access_token",accessToken);
    // response.setHeader("Refresh_token",refreshToken);

    //Tokens written on response body
    Map<String, String> tokens = new HashMap<>();
    tokens.put("access_token", accessToken);
    tokens.put("refresh_token", refreshToken);

    //write value to output stream
    new ObjectMapper().writeValue(response.getOutputStream(), tokens);
  }

  @Override
  protected void unsuccessfulAuthentication(HttpServletRequest request,
      HttpServletResponse response, AuthenticationException failed)
      throws IOException, ServletException {
    super.unsuccessfulAuthentication(request, response, failed);
  }
}
