package br.com.luizalabs.test.exceptions;

import java.util.UUID;

public class ProductNotFoundException extends  Exception{
    public ProductNotFoundException(UUID id){
        super("The product with id " + id + " not exists");
    }
}
