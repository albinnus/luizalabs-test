package br.com.luizalabs.test.repositories;

import br.com.luizalabs.test.entities.Product;
import br.com.luizalabs.test.exceptions.ProductNotFoundException;

import java.util.UUID;


public interface ProductRepository {
    Product getProductById(UUID id) throws ProductNotFoundException;

}
