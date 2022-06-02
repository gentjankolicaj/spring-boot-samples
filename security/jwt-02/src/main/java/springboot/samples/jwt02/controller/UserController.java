package springboot.samples.jwt02.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.samples.jwt02.domain.AppUser;
import springboot.samples.jwt02.service.UserService;

import java.util.List;

@RestController
@RequestMapping(UserController.URI)
@RequiredArgsConstructor
@Slf4j
public class UserController {
    static final String URI="api/v1/user";

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<AppUser>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }

}
