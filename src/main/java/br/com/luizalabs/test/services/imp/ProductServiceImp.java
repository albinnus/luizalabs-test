package br.com.luizalabs.test.services.imp;

import br.com.luizalabs.test.entities.Product;
import br.com.luizalabs.test.exceptions.ProductNotFoundException;
import br.com.luizalabs.test.repositories.ProductRepository;
import br.com.luizalabs.test.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;

    @Override
    @CachePut(cacheNames ="Product", key = "#id")
    public Product findById(UUID id) throws ProductNotFoundException {
        return productRepository.getProductById(id);
    }
}
