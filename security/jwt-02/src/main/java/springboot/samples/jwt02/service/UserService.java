package springboot.samples.jwt02.service;

import springboot.samples.jwt02.domain.AppRole;
import springboot.samples.jwt02.domain.AppUser;

import java.util.List;


public interface UserService {

    AppUser saveUser(AppUser user);
    AppRole saveRole(AppRole role);
    void addRoleToUser(String username,String roleName);
    AppUser getUser(String username);
    AppUser getUser(Long userId);
    List<AppUser> getUsers();
    
}
