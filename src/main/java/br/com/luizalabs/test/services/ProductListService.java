package br.com.luizalabs.test.services;

import br.com.luizalabs.test.entities.Product;

public interface ProductListService {
    void addProduct(Product product, Long userId);
}
