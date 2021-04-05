package br.com.luizalabs.test.services.imp;

import br.com.luizalabs.test.dtos.ProductListPaginationDto;
import br.com.luizalabs.test.entities.Client;
import br.com.luizalabs.test.entities.Product;
import br.com.luizalabs.test.entities.ProductList;
import br.com.luizalabs.test.exceptions.ClientException;
import br.com.luizalabs.test.exceptions.ClientNotExistsException;
import br.com.luizalabs.test.exceptions.ProductNotFoundException;
import br.com.luizalabs.test.repositories.ProductListRepository;
import br.com.luizalabs.test.services.ClientService;
import br.com.luizalabs.test.services.ProductListService;
import br.com.luizalabs.test.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductListServiceImp implements ProductListService {

    private final ProductListRepository productListRepository;
    private final ProductService productService;
    private final ClientService clientService;

    @Value("${product-list.size-pagination}")
    private Integer sizePagination;

    @Override
    public void addProduct(UUID productId, Long userId) throws ProductNotFoundException, ClientNotExistsException, ClientException {
        Product product = productService.findById(productId);
        Client client = clientService.findById(userId);
        if(!productListRepository.existsProductInList(product.getId(), client.getId()))
            productListRepository.addProduct(product, client.getId());
    }

    @Override
    public ProductListPaginationDto productList(Long userId, Integer page) {
        Integer actualPage = (page>0)?page-1:1;
        Integer offset = actualPage*sizePagination;
        ProductList productList = productListRepository.listProducts(userId, offset, sizePagination).orElse(productListBuilder());
        return productList.toPaginationDto();
    }

    public ProductList productListBuilder(){
        return ProductList.builder().list(new HashSet<>()).build();
    }


}
