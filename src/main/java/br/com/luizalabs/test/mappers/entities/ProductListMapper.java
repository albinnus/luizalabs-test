package br.com.luizalabs.test.mappers.entities;


import br.com.luizalabs.test.dtos.ProductListPaginationDto;
import br.com.luizalabs.test.entities.ProductList;
import org.springframework.stereotype.Component;

@Component
public class ProductListMapper {
    public ProductListPaginationDto toPaginationDto(ProductList productList){
        return ProductListPaginationDto.builder().products(productList.getList()).build();
    }
}
