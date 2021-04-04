package br.com.luizalabs.test.exceptions;

public class ClientException extends Exception {
    public ClientException(String errorMessage) {
        super(errorMessage);
    }
}
