package com.lw.exception;

/**
 * @author liwu
 * @create 2020-03-03 15:56
 */
public class LoginException extends RuntimeException {

    public LoginException(){

    }

    public LoginException(String message){
        super(message);
    }
}
