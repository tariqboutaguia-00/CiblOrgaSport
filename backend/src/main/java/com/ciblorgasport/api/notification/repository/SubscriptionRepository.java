package com.ciblorgasport.api.notification.repository;

import com.ciblorgasport.api.notification.entity.Subscription;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    List<Subscription> findByUserId(Long userId);

    List<Subscription> findByType(String type);

    boolean existsByUserIdAndType(Long userId, String type);
}