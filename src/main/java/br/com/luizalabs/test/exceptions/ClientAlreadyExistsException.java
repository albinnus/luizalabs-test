package br.com.luizalabs.test.exceptions;

public class ClientAlreadyExistsException  extends  Exception{


    public ClientAlreadyExistsException(String email){
        super("The cliente with email " + email + " already exists");
    }

}
