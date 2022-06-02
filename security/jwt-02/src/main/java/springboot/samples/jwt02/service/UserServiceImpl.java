package springboot.samples.jwt02.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot.samples.jwt02.domain.AppRole;
import springboot.samples.jwt02.domain.AppUser;
import springboot.samples.jwt02.exception.UserNotFoundException;
import springboot.samples.jwt02.repository.RoleRepository;
import springboot.samples.jwt02.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor //Create constructor with all args
@Transactional //make every operation transactional
@Slf4j  //Adds log field
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;



    @Override
    public AppUser saveUser(AppUser user) {
        return null;
    }

    @Override
    public AppRole saveRole(AppRole role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
      AppUser user=userRepository.findByUsername(username);
      AppRole role=roleRepository.findByName(roleName);
      if(user!=null){
          user.addRole(role);
      }else throw new UserNotFoundException(String.format("User with username %s not found.",username));
    }

    @Override
    public AppUser getUser(String username) {
        AppUser user=userRepository.findByUsername(username);
        if(user==null)
            throw new UserNotFoundException(String.format("User with username %s not found.",username));
        return user;
    }

    @Override
    public AppUser getUser(Long userId) {
        Optional<AppUser> optionalAppUser=userRepository.findById(userId);
        if(optionalAppUser.isEmpty())
            throw new UserNotFoundException(String.format("User with id %s not found.",userId));
        return optionalAppUser.get();
    }

    @Override
    public List<AppUser> getUsers() {
                return userRepository.findAll();
    }
}
