package com.ciblorgasport.api.volunteer.entity;

import com.ciblorgasport.api.user.entity.User;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "volunteers")
public class Volunteer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "cen_number", nullable = false, length = 100)
    private String cenNumber;

    @Column(nullable = false, length = 150)
    private String federation;

    @Column(name = "accreditation_status", nullable = false, length = 50)
    private String accreditationStatus;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public Volunteer() {
    }

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCenNumber() {
        return cenNumber;
    }

    public void setCenNumber(String cenNumber) {
        this.cenNumber = cenNumber;
    }

    public String getFederation() {
        return federation;
    }

    public void setFederation(String federation) {
        this.federation = federation;
    }

    public String getAccreditationStatus() {
        return accreditationStatus;
    }

    public void setAccreditationStatus(String accreditationStatus) {
        this.accreditationStatus = accreditationStatus;
    }
}