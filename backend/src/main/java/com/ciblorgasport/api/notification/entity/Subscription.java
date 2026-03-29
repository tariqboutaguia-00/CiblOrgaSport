package com.ciblorgasport.api.notification.entity;

import com.ciblorgasport.api.user.entity.User;
import jakarta.persistence.*;

@Entity
@Table(name = "subscriptions")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type; // SPORT, FAN_ZONE, SECURITY

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    public Subscription() {
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}