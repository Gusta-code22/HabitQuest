package br.com.Gusta_code22.habitquest.exception;

import org.springframework.security.core.AuthenticationException;

public class InvalidHeroCredentialsException extends AuthenticationException {
    
    public InvalidHeroCredentialsException(String msg) {
        super(msg);
    }
}