package com.smartroutine.user.security;

import com.smartroutine.user.entity.Role;
import com.smartroutine.user.entity.User;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Getter
public class CustomOAuth2User implements OAuth2User {

    private final User user;
    private final Map<String, Object> attributes;
    private final Collection<? extends GrantedAuthority> authorities;

    public CustomOAuth2User(User user, Map<String, Object> attributes, Collection<? extends GrantedAuthority> authorities) {
        this.user = user;
        this.attributes = attributes;
        this.authorities = authorities;
    }

    public UUID getUserId() {
        return user.getUserId();
    }

    public String getUsername() {
        return user.getName();
    }

    public String getEmail() {
        return user.getEmail();
    }

    public Role getRole() {
        return user.getRole();
    }

    @Override
    public String getName() {
        return user.getUserId().toString();
    }

}
