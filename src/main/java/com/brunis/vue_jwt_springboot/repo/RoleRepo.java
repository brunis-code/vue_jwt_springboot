package com.brunis.vue_jwt_springboot.repo;

import com.brunis.vue_jwt_springboot.model.ERole;
import com.brunis.vue_jwt_springboot.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepo  extends JpaRepository<Role, UUID> {
    Optional<Role> getByName(ERole erole);
}
