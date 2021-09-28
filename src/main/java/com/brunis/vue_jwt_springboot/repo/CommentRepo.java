package com.brunis.vue_jwt_springboot.repo;


import com.brunis.vue_jwt_springboot.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentRepo extends JpaRepository<Comment, Long> {
}
