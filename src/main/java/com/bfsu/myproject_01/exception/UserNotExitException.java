package com.bfsu.myproject_01.exception;

public class UserNotExitException extends RuntimeException{

    public UserNotExitException() {
        super("用户不存在!!yonghubucunzai!");
    }
}
