package br.com.luizalabs.test.repositories.apis;

import br.com.luizalabs.test.entities.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "${api.product.url}", url ="${api.product.url}" )
public interface ProductRepositoryClient {

    @GetMapping("/{id}/")
    Product getProductById(@PathVariable UUID id);
}
