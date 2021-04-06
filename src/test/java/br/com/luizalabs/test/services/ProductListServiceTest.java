package br.com.luizalabs.test.services;

import br.com.luizalabs.test.entities.Client;
import br.com.luizalabs.test.entities.Product;
import br.com.luizalabs.test.entities.ProductList;
import br.com.luizalabs.test.repositories.ProductListRepository;
import br.com.luizalabs.test.services.imp.ProductListServiceImp;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = "product-list.size-pagination=1")
public class ProductListServiceTest {

    private final ProductListRepository productListRepository = mock(ProductListRepository.class);
    private final ProductService productService = mock(ProductService.class) ;
    private final ClientService clientService = mock(ClientService.class);

    private final ProductListService productListService = new ProductListServiceImp(productListRepository, productService, clientService);

    private Product product;
    private Client client;

    @BeforeEach
    void setup(){
        product = Product.builder().id(UUID.fromString("bfeef866-73f2-4f3a-87cf-c4161214324b")).brand("teste").price(Float.parseFloat("30.00")).image("teste").title("teste").build();
        client = Client.builder().id(1L).name("teste").email("teste@teste.com").build();

    }

    @Test
    @SneakyThrows
    void addProduct(){
        when(productService.findById(UUID.fromString("bfeef866-73f2-4f3a-87cf-c4161214324b"))).thenReturn(product);
        when(clientService.findById(1L)).thenReturn(client);
        when(productListRepository.existsProductInList(product.getId(), client.getId())).thenReturn(false);
        productListService.addProduct(UUID.fromString("bfeef866-73f2-4f3a-87cf-c4161214324b"), 1L);
        verify(productListRepository, times(1)).addProduct(product, client.getId());
    }

    @Test
    @SneakyThrows
    void addProductInList(){
        when(productService.findById(UUID.fromString("bfeef866-73f2-4f3a-87cf-c4161214324b"))).thenReturn(product);
        when(clientService.findById(1L)).thenReturn(client);
        when(productListRepository.existsProductInList(product.getId(), client.getId())).thenReturn(true);
        productListService.addProduct(UUID.fromString("bfeef866-73f2-4f3a-87cf-c4161214324b"), 1L);
        verify(productListRepository, times(0)).addProduct(product, client.getId());
    }


    void create(){

    }

    void deleteByUserId(){

    }

}
