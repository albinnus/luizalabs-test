package br.com.luizalabs.test.repositories.imp;

import br.com.luizalabs.test.entities.Product;
import br.com.luizalabs.test.entities.ProductList;
import br.com.luizalabs.test.exceptions.ProductListException;
import br.com.luizalabs.test.repositories.ProductListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ProductListRepositoryImp implements ProductListRepository {
    private final MongoTemplate mongoTemplate;
    private final MongoOperations mongoOperations;

    @Override
    public void create(ProductList productList) throws ProductListException {
        try {
            mongoTemplate.save(productList);
        }catch (Exception e){
            throw new ProductListException("Error to create product list ");
        }
    }

    @Override
    public void addProduct(Product product, Long userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        mongoTemplate.updateFirst(query,new Update().push("list",product),ProductList.class);
    }

    @Override
    public boolean existsLists(Long userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        return mongoTemplate.exists(query,ProductList.class);
    }

    @Override
    public boolean existsProductInList(UUID productId, Long userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId).andOperator(
                Criteria.where("list.id").is(productId)));
        return mongoTemplate.exists(query,ProductList.class);
    }


}
