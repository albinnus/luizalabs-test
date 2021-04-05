package br.com.luizalabs.test.exceptions;

public class UserInvalidCredencialException extends  Exception{
    public UserInvalidCredencialException(String errorMessage) {
        super(errorMessage);
    }

}
