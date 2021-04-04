package br.com.luizalabs.test.services;

import br.com.luizalabs.test.entities.Product;
import br.com.luizalabs.test.exceptions.ProductNotFoundException;

import java.util.UUID;

public interface ProductService {
    Product findById(UUID uuid) throws ProductNotFoundException;
}
