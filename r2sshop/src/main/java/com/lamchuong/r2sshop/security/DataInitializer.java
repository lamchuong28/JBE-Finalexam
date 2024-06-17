package com.lamchuong.r2sshop.security;

import com.lamchuong.r2sshop.model.Role;
import com.lamchuong.r2sshop.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;
import java.util.Optional;

@Configuration
public class DataInitializer {

    @Autowired
    private RoleRepository roleRepository;

    @PostConstruct
    public void init() {
        createRoleIfNotFound("USER", "Default role for newly created users");
        createRoleIfNotFound("ADMIN", "Administrator role");
    }

    private void createRoleIfNotFound(String name, String description) {
        Optional<Role> roleOpt = roleRepository.findByName(name);
        if (roleOpt.isEmpty()) {
            Role role = new Role();
            role.setName(name);
            role.setDescription(description);
            roleRepository.save(role);
        }
    }
}
