package com.arohau.config;

import com.arohau.domain.Role;
import com.arohau.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class RoleInitializer implements CommandLineRunner {
    private final RoleRepository roleRepository;

    public RoleInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        List.of("ROLE_USER", "ROLE_ADMIN")
            .forEach(roleName -> 
                roleRepository
                    .findByName(roleName)
                    .orElseGet(
                        () -> roleRepository.save(new Role(roleName))
                    )
        );
    }
}
