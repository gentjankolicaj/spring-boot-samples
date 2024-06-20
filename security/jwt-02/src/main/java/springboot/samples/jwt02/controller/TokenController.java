package springboot.samples.jwt02.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.samples.jwt02.domain.AppUser;
import springboot.samples.jwt02.service.UserService;

@RestController
@RequestMapping(TokenController.TOKEN_URI)
@Slf4j
@RequiredArgsConstructor
public class TokenController {

  static final String TOKEN_URI = "api/v1/token";
  private final UserService userService;

  public void refreshToken(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
      if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
        String refreshToken = authorizationHeader.substring("Bearer ".length());
        Algorithm algorithm = Algorithm.HMAC256("G!idSo*Y1S&q".getBytes());
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();

        //Decoded json web token
        DecodedJWT decodedJWT = jwtVerifier.verify(refreshToken);

        //Load user from db
        String username = decodedJWT.getSubject();
        AppUser user = userService.getUser(username);

        //Generate new access token
        //withSubject() -> //Subject something unique about user ,example userId or username is if is unique
        //withClaims() ->
        //withExpiresAt() -> //Token expiry time minutes after expires
        //sign() -> Signs token wih chosen algorithm
        String accessToken = JWT.create()
            .withSubject(username)
            .withExpiresAt(new Date(System.currentTimeMillis() * 10 * 60 * 1000))
            .withClaim("roles",
                user.getRoles().stream().map(role -> role.getName()).collect(Collectors.toList()))
            .sign(algorithm);

        //Add refresh_token & access_token to map
        //Write map to response output stream
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("access_token", accessToken);
        tokenMap.put("refresh_token", refreshToken);

        //Set response content type
        response.setContentType(APPLICATION_JSON_VALUE);

        //Write map to httpServletResponse stream
        new ObjectMapper().writeValue(response.getOutputStream(), tokenMap);

      } else {
        throw new RuntimeException("Refresh token not found.");
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


