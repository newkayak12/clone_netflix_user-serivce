package com.netflix_clone.userservice.repository.user;

import com.netflix_clone.userservice.repository.domains.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Account, Long>, UserRepositoryCustom {
}
