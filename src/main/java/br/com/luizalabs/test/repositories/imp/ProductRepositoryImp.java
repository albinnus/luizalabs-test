package br.com.luizalabs.test.repositories.imp;

import br.com.luizalabs.test.entities.Product;
import br.com.luizalabs.test.exceptions.ProductNotFoundException;
import br.com.luizalabs.test.repositories.ProductRepository;
import br.com.luizalabs.test.repositories.apis.ProductRepositoryClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductRepositoryImp implements ProductRepository {
    private final ProductRepositoryClient productRepositoryClient;

    @Override
    public Product getProductById(UUID id) throws ProductNotFoundException {
       try {
           return productRepositoryClient.getProductById(id);
       }catch (FeignException f){
           throw new ProductNotFoundException(id);
       }
    }



}
