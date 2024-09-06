package com.Compari.back;

import com.Compari.back.entity.Role;
import com.Compari.back.RequestDto.UserRequestDto;
import com.Compari.back.auth.AuthenticationService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            AuthenticationService service
    ) {
        return args -> {
            var admin = UserRequestDto.builder()
                    .username("admin") // Adjusted from firstname
                    .email("admin@mail.com")
                    .password("password")
                    .image("") // If applicable
                    .role(Role.ADMIN) // Ensure this matches your Role setup
                    .build();
            
            // Ensure AuthenticationService is updated to handle UserRequestDto
            var response = service.register(admin); // Adjust if needed
            System.out.println("Admin token: " + response.getAccessToken());
        };
    }
}
