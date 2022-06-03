package springboot.samples.jwt02.security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class CustomAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Check if trying to log in
        if(request.getServletPath().equals("/api/v1/login")){
            filterChain.doFilter(request,response);
        }else{
            try {
                String authorizationHeader = request.getHeader("Authorization");
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
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    filterChain.doFilter(request,response);
                }
            }catch (Exception e){

            }
        }
    }

    private Collection<SimpleGrantedAuthority> getSimpleGrantedAuthorities(String[] roles) {
        Collection<SimpleGrantedAuthority> authorities=new ArrayList<>();
        if(roles!=null && roles.length!=0){
            for(String role:roles)
                authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }

}
