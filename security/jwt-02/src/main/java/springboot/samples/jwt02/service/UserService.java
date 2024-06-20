package springboot.samples.jwt02.service;

import java.util.List;
import springboot.samples.jwt02.domain.AppRole;
import springboot.samples.jwt02.domain.AppUser;
import springboot.samples.jwt02.dto.RoleToUserForm;


public interface UserService {

  AppUser saveUser(AppUser user);

  AppRole saveRole(AppRole role);

  void addRoleToUser(RoleToUserForm roleToUserForm);

  AppUser getUser(String username);

  AppUser getUser(Long userId);

  List<AppUser> getUsers();

}
