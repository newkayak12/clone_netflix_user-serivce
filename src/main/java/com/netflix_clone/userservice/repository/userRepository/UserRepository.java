package com.netflix_clone.userservice.repository.userRepository;

import com.netflix_clone.userservice.repository.domains.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Account, Long>, UserRepositoryCustom {
    Optional<Long> countAccountByUserId(String userId);
    Optional<Account> findAccountByUserId(String userId);
}
