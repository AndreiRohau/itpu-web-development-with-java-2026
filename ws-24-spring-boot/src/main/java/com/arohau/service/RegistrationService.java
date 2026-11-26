package com.arohau.service;

import com.arohau.domain.AppUser;
import com.arohau.domain.Role;
import com.arohau.repository.AppUserRepository;
import com.arohau.repository.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

@Service
public class RegistrationService {
    private static final String DEFAULT_ROLE = "ROLE_USER";

    private final AppUserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationService(AppUserRepository userRepository,
                               RoleRepository roleRepository,
                               PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public AppUser register(RegistrationForm form) {
        String username = form.getUsername().trim().toLowerCase(Locale.ROOT);
        String email = form.getEmail().trim().toLowerCase(Locale.ROOT);

        if (userRepository.existsByUsernameIgnoreCase(username)) {
            throw new DuplicateRegistrationException("username", "That username is already registered.");
        }
        if (userRepository.existsByEmailIgnoreCase(email)) {
            throw new DuplicateRegistrationException("email", "That email address is already registered.");
        }

        Role userRole = roleRepository.findByName(DEFAULT_ROLE)
                .orElseThrow(() -> new IllegalStateException("Default user role has not been initialized."));

        AppUser user = new AppUser(username, email, passwordEncoder.encode(form.getPassword()));
        user.getRoles().add(userRole);
        return userRepository.save(user);
    }
}
