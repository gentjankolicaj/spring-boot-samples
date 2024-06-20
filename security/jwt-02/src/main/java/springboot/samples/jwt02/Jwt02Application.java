package springboot.samples.jwt02;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springboot.samples.jwt02.domain.AppRole;
import springboot.samples.jwt02.domain.AppUser;
import springboot.samples.jwt02.dto.RoleToUserForm;
import springboot.samples.jwt02.service.UserService;

@SpringBootApplication
public class Jwt02Application {

  public static void main(String[] args) {
    SpringApplication.run(Jwt02Application.class, args);
  }


  //@Bean makes run method being invoked after application startup & schema creation
  @Bean
  CommandLineRunner run(UserService userService) {
    return args -> {
      userService.saveRole(new AppRole(null, "ANON"));
      userService.saveRole(new AppRole(null, "USER"));
      userService.saveRole(new AppRole(null, "MANAGER"));
      userService.saveRole(new AppRole(null, "ADMIN"));

      userService.saveUser(new AppUser(null, "john0", "doe", "john117", "cortana", null));
      userService.saveUser(new AppUser(null, "john1", "doe1", "john118", "cortana1", null));
      userService.saveUser(new AppUser(null, "john2", "doe2", "john119", "cortana2", null));
      userService.saveUser(new AppUser(null, "john3", "doe3", "john120", "cortana3", null));

      userService.addRoleToUser(new RoleToUserForm("john117", "ANON"));
      userService.addRoleToUser(new RoleToUserForm("john117", "USER"));
      userService.addRoleToUser(new RoleToUserForm("john117", "MANAGER"));
      userService.addRoleToUser(new RoleToUserForm("john117", "ADMIN"));

    };
  }

}
