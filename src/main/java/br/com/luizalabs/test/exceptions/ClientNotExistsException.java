package br.com.luizalabs.test.exceptions;

public class ClientNotExistsException extends  Exception{
    public ClientNotExistsException(Long id){
        super("The cliente with id " + id + " not exists");
    }
}
