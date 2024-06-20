package springboot.samples.jwt02.security.authentication;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import springboot.samples.jwt02.domain.AppRole;
import springboot.samples.jwt02.domain.AppUser;
import springboot.samples.jwt02.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<AppUser> appUserOptional = userRepository.findByUsername(username);
    if (appUserOptional.isEmpty()) {
      log.error("User with username {} not found", username);
      throw new UsernameNotFoundException(
          String.format("User with username {} not found", username));
    }
    log.info("User with username {} found.", username);
    AppUser user = appUserOptional.get();
    String password = user.getPassword();
    String dbUsername = user.getUsername();

    Collection<SimpleGrantedAuthority> authorities = getSimpleGrantedAuthorities(user.getRoles());
    return new CustomUserDetails(dbUsername, password, authorities);
  }

  private Collection<SimpleGrantedAuthority> getSimpleGrantedAuthorities(
      Collection<AppRole> roles) {
    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
    if (roles != null && !roles.isEmpty()) {
      roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
    }
    return authorities;
  }
}
