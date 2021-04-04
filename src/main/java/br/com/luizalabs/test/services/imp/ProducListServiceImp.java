package br.com.luizalabs.test.services.imp;

import br.com.luizalabs.test.entities.Product;
import br.com.luizalabs.test.entities.ProductList;
import br.com.luizalabs.test.repositories.ProductListRepository;
import br.com.luizalabs.test.services.ProductListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProducListServiceImp implements ProductListService {

    private final ProductListRepository productListRepository;

    @Override
    public void addProduct(Product product, Long userId) {
        if(!productListRepository.existsProductInList(product.getId(), userId))
            productListRepository.addProduct(product, userId);
    }
}
