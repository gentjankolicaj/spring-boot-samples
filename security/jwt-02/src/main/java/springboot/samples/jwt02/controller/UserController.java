package springboot.samples.jwt02.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import springboot.samples.jwt02.domain.AppRole;
import springboot.samples.jwt02.domain.AppUser;
import springboot.samples.jwt02.dto.RoleToUserForm;
import springboot.samples.jwt02.service.UserService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(UserController.USER_URI)
@RequiredArgsConstructor
@Slf4j
public class UserController {
    static final String USER_URI="api/v1";

    private final UserService userService;

    @GetMapping("/user")
    public ResponseEntity<List<AppUser>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/user")
    public ResponseEntity<AppUser> saveUser(@RequestBody AppUser user){
        URI uri=URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/user").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @PostMapping("/role")
    public ResponseEntity<AppRole> saveRole(@RequestBody AppRole role){
        URI uri=URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/role").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/role/add_to_user")
    public ResponseEntity<AppRole> addRoleToUser(@RequestBody RoleToUserForm roleToUserForm){
        userService.addRoleToUser(roleToUserForm);
        return ResponseEntity.ok().build();
    }



}
