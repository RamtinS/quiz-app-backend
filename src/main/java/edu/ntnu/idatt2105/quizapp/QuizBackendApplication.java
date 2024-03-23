package edu.ntnu.idatt2105.quizapp;

import edu.ntnu.idatt2105.quizapp.model.Role;
import edu.ntnu.idatt2105.quizapp.model.User;
import edu.ntnu.idatt2105.quizapp.repositories.RoleRepository;
import edu.ntnu.idatt2105.quizapp.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class QuizBackendApplication {

  public static void main(String[] args) {
    SpringApplication.run(QuizBackendApplication.class, args);
  }

  @Bean
  CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {

    return args -> {

      if (roleRepository.findByAuthority("ADMIN").isPresent()) return;

      Role adminRole = roleRepository.save(
              Role.builder()
                      .authority("ADMIN")
                      .build());

      roleRepository.save(Role.builder()
              .authority("USER")
              .build());

      Set<Role> roles = new HashSet<>();
      roles.add(adminRole);

      User user = User.builder()
              .userId(1L)
              .username("admin")
              .password(passwordEncoder.encode("password"))
              .authorities(roles)
              .build();

      userRepository.save(user);
    };
  }
}
