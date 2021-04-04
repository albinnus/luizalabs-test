package br.com.luizalabs.test.repositories;

import br.com.luizalabs.test.entities.Product;
import br.com.luizalabs.test.exceptions.ProductNotFoundException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;


public interface ProductRepository {
    Product getProductById(UUID id) throws ProductNotFoundException;
}
