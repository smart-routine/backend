package com.smartroutine.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="users")
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User{

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Provider provider;

    @Column(name = "provider_id", nullable = false, unique = true)
    private String providerId;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(length = 2000)
    private String googleAccessToken;

    @Column(length = 2000)
    private String googleRefreshToken;

    @Column
    private LocalDateTime googleTokenExpiresAt;

    @Column(name = "created_at", nullable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Builder
    public User(Provider provider, String providerId, String email, String name, String profileImageUrl, Role role) {
        this.provider = provider == null ? Provider.GOOGLE : provider;
        this.providerId = providerId;
        this.email = email;
        this.name = name;
        this.profileImageUrl = profileImageUrl;
        this.role = (role == null) ? Role.USER : role;
    }

    public void updateGoogleToken(
        String accessToken,
        String refreshToken,
        LocalDateTime expiresAt
    ){
        this.googleAccessToken = accessToken;
        if(refreshToken != null) this.googleRefreshToken = refreshToken;
        this.googleTokenExpiresAt = expiresAt;
    }

}
