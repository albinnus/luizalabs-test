package br.com.luizalabs.test.services;

import br.com.luizalabs.test.dtos.ProductListPaginationDto;
import br.com.luizalabs.test.exceptions.ClientException;
import br.com.luizalabs.test.exceptions.ClientNotExistsException;
import br.com.luizalabs.test.exceptions.ProductNotFoundException;

import java.util.UUID;

public interface ProductListService {
    void addProduct(UUID productId, Long userId) throws ProductNotFoundException, ClientNotExistsException, ClientException;
    ProductListPaginationDto productList(Long userId, Integer page);
}
