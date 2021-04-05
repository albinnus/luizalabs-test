package br.com.luizalabs.test.dtos;

import br.com.luizalabs.test.entities.Product;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;

@Data
@Builder
public class ProductListPaginationDto implements Serializable {
    private HashSet<Product> products;
}
