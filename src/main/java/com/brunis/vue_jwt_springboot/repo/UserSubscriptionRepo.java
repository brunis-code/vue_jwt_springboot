package com.brunis.vue_jwt_springboot.repo;


import com.brunis.vue_jwt_springboot.model.User;
import com.brunis.vue_jwt_springboot.model.UserSubscription;
import com.brunis.vue_jwt_springboot.model.UserSubscriptionId;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface UserSubscriptionRepo extends JpaRepository<UserSubscription, UserSubscriptionId> {
    List<UserSubscription> findBySubscriber(User user);

    List<UserSubscription> findByChannel(User channel);

    UserSubscription findByChannelAndSubscriber(User channel, User subscriber);
}
