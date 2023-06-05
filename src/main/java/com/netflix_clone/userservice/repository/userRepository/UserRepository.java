package com.netflix_clone.userservice.repository.userRepository;

import com.netflix_clone.userservice.repository.domains.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Account, Long>, UserRepositoryCustom {
    @Query(nativeQuery = true, value = "SELECT :msg FROM DUAL;")
    String wakeUpMsg(@Param(value = "msg") String Msg);

    Optional<Long> countAccountByUserId(String userId);
    Optional<Account> findAccountByUserId(String userId);
    Optional<Account> findAccountByEmailAndMobileNo( String email, String mobileNo );
}
