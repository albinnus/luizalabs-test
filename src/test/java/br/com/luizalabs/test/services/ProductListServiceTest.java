package br.com.luizalabs.test.services;

import br.com.luizalabs.test.entities.Client;
import br.com.luizalabs.test.entities.Product;
import br.com.luizalabs.test.entities.ProductList;
import br.com.luizalabs.test.exceptions.ClientException;
import br.com.luizalabs.test.properties.ProductListProperties;
import br.com.luizalabs.test.repositories.ProductListRepository;
import br.com.luizalabs.test.services.imp.ProductListServiceImp;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ProductListServiceTest {

    private final ProductListRepository productListRepository = mock(ProductListRepository.class);
    private final ProductService productService = mock(ProductService.class) ;
    private final ClientService clientService = mock(ClientService.class);
    private final ProductListProperties productListProperties = mock(ProductListProperties.class);
    private final ProductListService productListService = new ProductListServiceImp(productListRepository, productService, clientService, productListProperties);

    private Product product;
    private Client client;

    @BeforeEach
    void setup(){
        product = Product.builder().id(UUID.fromString("bfeef866-73f2-4f3a-87cf-c4161214324b")).brand("teste").price(Float.parseFloat("30.00")).image("teste").title("teste").build();
        client = Client.builder().id(1L).name("teste").email("teste@teste.com").build();
        when(productListProperties.getSizePagination()).thenReturn(1);
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


    @Test
    @SneakyThrows
    void productList(){
        when(productListRepository.listProducts(1L,0,1)).thenReturn(Optional.of(ProductList.builder().list(new HashSet<>()).build()));
        ProductList productListMock = productListService.productList(1L, 1);
        verify(productListRepository,times(1)).listProducts(1L, 0, 1);
        assertEquals(new HashSet<Product>(), productListMock.getList());
    }

    @Test
    @SneakyThrows
    void create(){
        productListService.create(ProductList.builder().build());
        verify(productListRepository, times(1)).create(ProductList.builder().build());
    }

    @Test
    @SneakyThrows
    void deleteByUserId(){
        when(productListRepository.deleteByUserId(1L)).thenReturn(true);
        productListService.deleteByUserId(1L);
        verify(productListRepository,times(1)).deleteByUserId(1L);
    }

    @Test
    @SneakyThrows
    void deleteByUserIdException(){
        when(productListRepository.deleteByUserId(1L)).thenReturn(false);
        assertThrows(ClientException.class, () -> productListService.deleteByUserId(1L));
        verify(productListRepository,times(1)).deleteByUserId(1L);
    }
}
