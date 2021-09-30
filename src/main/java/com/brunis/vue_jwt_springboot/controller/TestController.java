package com.brunis.vue_jwt_springboot.controller;

import com.brunis.vue_jwt_springboot.security.services.UserDetailsImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.brunis.vue_jwt_springboot.dto.MessagePageDto;
import com.brunis.vue_jwt_springboot.model.User;
import com.brunis.vue_jwt_springboot.model.Views;
import com.brunis.vue_jwt_springboot.repo.UserRepo;
import com.brunis.vue_jwt_springboot.service.MessageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
    private final MessageService messageService;
    private final UserRepo userDetailsRepo;
    private final ObjectWriter messageWriter;
    private final ObjectWriter profileWriter;


    @Autowired
    public TestController(MessageService messageService, UserRepo userDetailsRepo, ObjectMapper mapper) {
        this.messageService = messageService;
        this.userDetailsRepo = userDetailsRepo;
        ObjectMapper objectMapper = mapper
                .setConfig(mapper.getSerializationConfig());

        this.messageWriter = objectMapper
                .writerWithView(Views.FullMessage.class);
        this.profileWriter = objectMapper
                .writerWithView(Views.FullProfile.class);
    }

    @GetMapping("/all")
    public String allAccess() throws JsonProcessingException {
        String messages = "";
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (userDetails  != null) {
            User user = userDetailsRepo.findById(userDetails.getId()).get();
            Sort sort = Sort.by(Sort.Direction.DESC, "id");
            PageRequest pageRequest = PageRequest.of(0, MessageController.MESSAGES_PER_PAGE, sort);
            MessagePageDto messagePageDto = messageService.findForUser(pageRequest, user);
            messages = messageWriter.writeValueAsString(messagePageDto.getMessages());
        }
        return messages;
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    public String moderatorAccess() {
        return "Moderator Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {

        return "Admin Board.";
    }
}
