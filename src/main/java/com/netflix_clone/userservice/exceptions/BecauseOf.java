package com.netflix_clone.userservice.exceptions;

import lombok.Getter;

@Getter
public enum BecauseOf {
    ACCOUNT_NOT_EXIST("아이디 혹은 비밀번호를 확인해주세요."),
    PASSWORD_NOT_MATCHED("아이디 혹은 비밀번호를 확인해주세요.");


    private String msg;
    BecauseOf(String msg){
        this.msg = msg;
    }
}
