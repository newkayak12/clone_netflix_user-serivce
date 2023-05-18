package com.netflix_clone.userservice.repository;

import com.netflix_clone.userservice.exceptions.BecauseOf;
import com.netflix_clone.userservice.repository.dto.request.SignInRequest;
import com.netflix_clone.userservice.repository.user.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.config.BootstrapMode;

import static org.assertj.core.api.Assertions.*;
@DataJpaTest(bootstrapMode = BootstrapMode.DEFAULT, showSql = true)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Profile("local")
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Test
    @DisplayName("로그인 테스트")
    public void signInTest(){
        //given
        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setUserId("test");
        signInRequest.setUserPwd("pwd");


        assertThatThrownBy(() -> {
            repository.signIn(signInRequest);
        })
        .hasMessage(BecauseOf.ACCOUNT_NOT_EXIST.getMsg());

    }
}
