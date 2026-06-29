package com.smartroutine.user.repository;

import com.smartroutine.user.entity.Provider;
import com.smartroutine.user.entity.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByProviderAndProviderId(Provider provider, String providerId);
}
