package springboot.samples.jwt02.security.filter;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
public class CustomAuthorizationFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    //Check if request path is directed to login or refresh token
    if (request.getServletPath().equals("api/v1/login") || request.getServletPath()
        .equals("api/v1/token/refresh")) {
      filterChain.doFilter(request, response);
    } else {
      try {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
          String token = authorizationHeader.substring("Bearer ".length());
          Algorithm algorithm = Algorithm.HMAC256("G!idSo*Y1S&q".getBytes());
          JWTVerifier jwtVerifier = JWT.require(algorithm).build();

          //Decoded json web token
          DecodedJWT decodedJWT = jwtVerifier.verify(token);
          String username = decodedJWT.getSubject();
          String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
          Collection<SimpleGrantedAuthority> authorities = getSimpleGrantedAuthorities(roles);

          //Create authentication token to assign SecurityContextHolder
          UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
              username, null, authorities);
          SecurityContextHolder.getContext().setAuthentication(authenticationToken);
          filterChain.doFilter(request, response);
        }
      } catch (Exception e) {
        log.error("Error in {}", e.getMessage());
        response.setHeader("error", e.getMessage());

        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error", e.getMessage());
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), errorMap);
      }
    }
  }

  private Collection<SimpleGrantedAuthority> getSimpleGrantedAuthorities(String[] roles) {
    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
    if (roles != null) {
      for (String role : roles) {
        authorities.add(new SimpleGrantedAuthority(role));
      }
    }
    return authorities;
  }

}
