package com.netflix_clone.userservice.exceptions;

public class CommonException extends Exception{

    public CommonException(BecauseOf reason){
        super(reason.getMsg());
    }
}
