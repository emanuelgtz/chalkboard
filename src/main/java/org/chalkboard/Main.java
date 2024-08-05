package org.chalkboard;

import org.chalkboard.persistence.entity.PermissionEntity;
import org.chalkboard.persistence.entity.RoleEntity;
import org.chalkboard.enums.RoleEnum;
import org.chalkboard.persistence.entity.UserEntity;
import org.chalkboard.persistence.repository.UserEntityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class Main {
  public static void main(String[] args) {

    System.out.println("Application running");
    SpringApplication.run(Main.class, args);
  }

  @Bean
  CommandLineRunner init(UserEntityRepository userRepository, PasswordEncoder passwordEncoder) {
    return args -> {
      // create sql records to fill our sql tables up
      // 1. CREATE PERMISSIONS
      PermissionEntity createPermission = PermissionEntity.builder()
              .permission("CREATE")
              .build();

      PermissionEntity readPermission = PermissionEntity.builder()
              .permission("READ")
              .build();

      PermissionEntity updatePermission = PermissionEntity.builder()
              .permission("UPDATE")
              .build();

      PermissionEntity deletePermission = PermissionEntity.builder()
              .permission("DELETE")
              .build();


      // 2. CREATE ROLES
      RoleEntity roleAdmin = RoleEntity.builder()
              // we're having just two roles, therefore we are going to make over the Role Admin the most important
              .roleEnum(RoleEnum.ADMIN)
              .permissionList(Set.of(
                      createPermission,
                      readPermission,
                      updatePermission,
                      deletePermission
              ))
              .build();

      RoleEntity roleUser = RoleEntity.builder()
              .roleEnum(RoleEnum.USER)
              .permissionList(Set.of(
                      createPermission,
                      readPermission
              ))
              .build();

      // 3. CREATE USERS
      UserEntity userEmanuel = UserEntity.builder()
              .name("Emanuel")
              .email("emanuel@gmail.com")
              .password("12345")
              .age(22)
              .isEnabled(true).accountNoExpired(true).accountNoLocked(true).credentialNoExpired(true)
              .roles(Set.of(roleUser))
              .build();

      UserEntity userMike = UserEntity.builder()
              .name("Mike")
              .email("mike@gmail.com")
              .password("12345")
              .age(25)
              .isEnabled(true).accountNoExpired(true).accountNoLocked(true).credentialNoExpired(true)
              .roles(Set.of(roleAdmin))
              .build();


      // user repository to save the users we made into the database table. .saveAll method is devoted to allow storing multiple elements at once.
      userRepository.saveAll(List.of(userMike, userEmanuel));

    };

  }


}
