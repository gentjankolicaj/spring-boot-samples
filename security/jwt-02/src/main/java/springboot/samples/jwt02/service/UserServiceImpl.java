package springboot.samples.jwt02.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot.samples.jwt02.domain.AppRole;
import springboot.samples.jwt02.domain.AppUser;
import springboot.samples.jwt02.dto.RoleToUserForm;
import springboot.samples.jwt02.exception.UserNotFoundException;
import springboot.samples.jwt02.repository.RoleRepository;
import springboot.samples.jwt02.repository.UserRepository;

@Service
@RequiredArgsConstructor //Create constructor with all args
@Transactional //make every operation transactional
@Slf4j  //Adds log field
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;


  @Override
  public AppUser saveUser(AppUser user) {
    log.info("Saving new user to db");
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepository.save(user);
  }

  @Override
  public AppRole saveRole(AppRole role) {
    log.info("Saving new role to db.");
    return roleRepository.save(role);
  }

  @Override
  public void addRoleToUser(RoleToUserForm roleToUserForm) {
    String username = roleToUserForm.getUsername();
    String roleName = roleToUserForm.getRoleName();
    log.info("Adding role: {} to user with username:{}", roleName, username);
    Optional<AppUser> optionalAppUser = userRepository.findByUsername(username);
    AppRole role = roleRepository.findByName(roleName);
    if (!optionalAppUser.isEmpty()) {
      optionalAppUser.get().addRole(role);
    } else {
      throw new UserNotFoundException(String.format("User with username %s not found.", username));
    }
  }

  @Override
  public AppUser getUser(String username) {
    log.info("Fetching user by username {}", username);
    Optional<AppUser> optionalAppUser = userRepository.findByUsername(username);
    if (optionalAppUser.isEmpty()) {
      throw new UserNotFoundException(String.format("User with username %s not found.", username));
    }
    return optionalAppUser.get();
  }

  @Override
  public AppUser getUser(Long userId) {
    log.info("Fetching user by id {}", userId);
    Optional<AppUser> optionalAppUser = userRepository.findById(userId);
    if (optionalAppUser.isEmpty()) {
      throw new UserNotFoundException(String.format("User with id %s not found.", userId));
    }
    return optionalAppUser.get();
  }

  @Override
  public List<AppUser> getUsers() {
    log.info("Fetching all users");
    return userRepository.findAll();
  }
}
