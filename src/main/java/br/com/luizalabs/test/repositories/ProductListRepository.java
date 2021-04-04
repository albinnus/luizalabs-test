package br.com.luizalabs.test.repositories;
import br.com.luizalabs.test.entities.Product;
import br.com.luizalabs.test.entities.ProductList;
import br.com.luizalabs.test.exceptions.ProductListException;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductListRepository {
    void create(ProductList productList) throws ProductListException;
    void addProduct(Product product, Long userId);
    boolean existsLists(Long userId);
    boolean existsProductInList(UUID productId, Long userId);
}
