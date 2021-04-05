package br.com.luizalabs.test.exceptions;

public class UserNotFoundException extends  Exception{
    public UserNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
