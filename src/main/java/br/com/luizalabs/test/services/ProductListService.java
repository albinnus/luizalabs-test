package br.com.luizalabs.test.services;

import br.com.luizalabs.test.dtos.ProductListPaginationDto;
import br.com.luizalabs.test.entities.ProductList;
import br.com.luizalabs.test.exceptions.ClientException;
import br.com.luizalabs.test.exceptions.ClientNotExistsException;
import br.com.luizalabs.test.exceptions.ProductListException;
import br.com.luizalabs.test.exceptions.ProductNotFoundException;

import java.util.UUID;

public interface ProductListService {
    void addProduct(UUID productId, Long userId) throws ProductNotFoundException, ClientNotExistsException, ClientException;
    ProductList productList(Long userId, Integer page);
    void create(ProductList productList) throws ProductListException;
    void deleteByUserId(Long id) throws ClientException;

}
