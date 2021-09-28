package com.brunis.vue_jwt_springboot.db_loader;

import com.brunis.vue_jwt_springboot.model.ERole;
import com.brunis.vue_jwt_springboot.model.Role;
import com.brunis.vue_jwt_springboot.model.User;
import com.brunis.vue_jwt_springboot.repo.RoleRepo;
import com.brunis.vue_jwt_springboot.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


import java.time.LocalDateTime;
@Component
public class DBLoader implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    RoleRepo roleRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    BCryptPasswordEncoder bcryptEncoder;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {


            final Role role_user = roleRepo.getByName(ERole.ROLE_USER).orElse(
                    roleRepo.save(new Role(ERole.ROLE_USER))
            );



            final Role role_admin = roleRepo.getByName(ERole.ROLE_ADMIN).orElse(
                    roleRepo.save(new Role(ERole.ROLE_ADMIN))
            );



            final Role role_sysadmin = roleRepo.getByName(ERole.ROLE_SYSADMIN).orElse(
                    roleRepo.save(new Role(ERole.ROLE_SYSADMIN))
            );


            userRepo.findByLogin("basis").orElse(createUser("basis", "basis", role_sysadmin, "basis@mail.ru"));
            userRepo.findByLogin("admin").orElse(createUser("admin", "admin", role_admin, "admin@mail.ru"));


        } catch (final Exception err) {
            System.out.println(err);
        }
    }

    User createUser(String name, String password, Role role, String email){
        User result = new User(name, email, bcryptEncoder.encode(password));
        result.getRoles().add(role);
        return userRepo.save(result);
    }
}
