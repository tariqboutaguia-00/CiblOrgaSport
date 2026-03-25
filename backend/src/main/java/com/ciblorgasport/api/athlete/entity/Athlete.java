package com.ciblorgasport.api.athlete.entity;

import com.ciblorgasport.api.user.entity.User;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "athletes")
public class Athlete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(nullable = false, length = 100)
    private String nationality;

    @Column(nullable = false)
    private LocalDate passportExpiration;

    @Column(nullable = false)
    private LocalDate medicalCertificateExpiration;

    @Column(nullable = false)
    private boolean charterAccepted;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public Athlete() {
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
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

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public LocalDate getPassportExpiration() {
        return passportExpiration;
    }

    public void setPassportExpiration(LocalDate passportExpiration) {
        this.passportExpiration = passportExpiration;
    }

    public LocalDate getMedicalCertificateExpiration() {
        return medicalCertificateExpiration;
    }

    public void setMedicalCertificateExpiration(LocalDate medicalCertificateExpiration) {
        this.medicalCertificateExpiration = medicalCertificateExpiration;
    }

    public boolean isCharterAccepted() {
        return charterAccepted;
    }

    public void setCharterAccepted(boolean charterAccepted) {
        this.charterAccepted = charterAccepted;
    }
}