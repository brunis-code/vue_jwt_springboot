package com.brunis.vue_jwt_springboot.service;

import com.brunis.vue_jwt_springboot.dto.EventType;
import com.brunis.vue_jwt_springboot.dto.ObjectType;
import com.brunis.vue_jwt_springboot.model.Comment;
import com.brunis.vue_jwt_springboot.model.User;
import com.brunis.vue_jwt_springboot.model.Views;
import com.brunis.vue_jwt_springboot.repo.CommentRepo;
import com.brunis.vue_jwt_springboot.util.WsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.function.BiConsumer;

@Service
public class CommentService {
    private final CommentRepo commentRepo;
    private final BiConsumer<EventType, Comment> wsSender;

    @Autowired
    public CommentService(CommentRepo commentRepo, WsSender wsSender) {
        this.commentRepo = commentRepo;
        this.wsSender = wsSender.getSender(ObjectType.COMMENT, Views.FullComment.class);
    }

    public Comment create(Comment comment, User user) {
        comment.setAuthor(user);
        Comment commentFromDb = commentRepo.save(comment);

        wsSender.accept(EventType.CREATE, commentFromDb);

        return commentFromDb;
    }
}
