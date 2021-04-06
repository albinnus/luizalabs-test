package br.com.luizalabs.test.exceptions;

import java.util.UUID;

public class ProductListRemoveException  extends  Exception{
    public ProductListRemoveException(UUID idProduct, Long idUser){
        super("Error delete product id "+idProduct.toString()+" in list user "+idUser);
    }
}