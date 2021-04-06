package br.com.luizalabs.test.services;

import br.com.luizalabs.test.entities.Product;
import br.com.luizalabs.test.exceptions.ProductNotFoundException;
import br.com.luizalabs.test.repositories.ProductRepository;
import br.com.luizalabs.test.repositories.imp.ProductRepositoryImp;
import br.com.luizalabs.test.services.imp.ProductServiceImp;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    private final ProductRepository productRepository = mock(ProductRepositoryImp.class);
    private final ProductService productService = new ProductServiceImp(productRepository);

    private Product product;

    @BeforeEach
    void setup(){
        product = Product.builder().id(UUID.fromString("bfeef866-73f2-4f3a-87cf-c4161214324b")).brand("teste").price(Float.parseFloat("30.00")).image("teste").title("teste").build();
    }

    @Test
    @SneakyThrows
    void findById(){
        when(productRepository.getProductById(UUID.fromString("bfeef866-73f2-4f3a-87cf-c4161214324b"))).thenReturn(product);
        Product productMock = productService.findById(UUID.fromString("bfeef866-73f2-4f3a-87cf-c4161214324b"));
        verify(productRepository, times(1)).getProductById(UUID.fromString("bfeef866-73f2-4f3a-87cf-c4161214324b"));
        assertEquals("teste",productMock.getBrand());
    }

    @Test
    @SneakyThrows
    void findByIdProductNotFoundException(){
        when(productRepository.getProductById(UUID.fromString("bfeef866-73f2-4f3a-87cf-c4161214324b"))).thenThrow(ProductNotFoundException.class);
        assertThrows(ProductNotFoundException.class, () -> productService.findById(UUID.fromString("bfeef866-73f2-4f3a-87cf-c4161214324b")));
        verify(productRepository, times(1)).getProductById(UUID.fromString("bfeef866-73f2-4f3a-87cf-c4161214324b"));
    }


}
