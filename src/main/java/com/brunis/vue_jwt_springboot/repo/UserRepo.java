package com.brunis.vue_jwt_springboot.repo;


import com.brunis.vue_jwt_springboot.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;
import java.util.UUID;

public interface UserRepo extends JpaRepository<User, String> {
    @EntityGraph(attributePaths = { "subscriptions", "subscribers" })
    Optional<User> findById(UUID s);
    Optional<User> findByLogin(String login);
    Boolean existsByEmail(String email);
    Boolean existsByLogin(String login);

}