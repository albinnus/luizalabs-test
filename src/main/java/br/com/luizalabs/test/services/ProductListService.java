package br.com.luizalabs.test.services;

import br.com.luizalabs.test.entities.ProductList;
import br.com.luizalabs.test.exceptions.*;

import java.util.UUID;

public interface ProductListService {
    void addProduct(UUID productId, Long userId) throws ProductNotFoundException, ClientNotExistsException, ClientException;
    ProductList productList(Long userId, Integer page) throws ClientNotExistsException, ClientException;
    void create(ProductList productList) throws ProductListException;
    void deleteByUserId(Long id) throws ClientException;
    void deleteProductInList(UUID productId, Long id) throws ProductNotFoundException, ProductListRemoveException, ClientNotExistsException, ClientException;
}
