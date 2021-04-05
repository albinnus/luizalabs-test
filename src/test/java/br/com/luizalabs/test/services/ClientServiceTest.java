package br.com.luizalabs.test.services;

import br.com.luizalabs.test.entities.Client;
import br.com.luizalabs.test.entities.ProductList;
import br.com.luizalabs.test.exceptions.ClientAlreadyExistsException;
import br.com.luizalabs.test.exceptions.ClientException;
import br.com.luizalabs.test.exceptions.ClientNotExistsException;
import br.com.luizalabs.test.repositories.ClientRepository;
import br.com.luizalabs.test.repositories.ProductListRepository;
import br.com.luizalabs.test.repositories.imp.ClientRepositoryImp;
import br.com.luizalabs.test.repositories.imp.ProductListRepositoryImp;
import br.com.luizalabs.test.services.imp.ClientServiceImp;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.HashSet;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
public class ClientServiceTest {

    private final ClientRepository clientRepository = mock(ClientRepositoryImp.class);
    private final ProductListRepository productListRepository = mock(ProductListRepositoryImp.class);
    private final ClientService clientService = new ClientServiceImp(clientRepository, productListRepository);
    private Client client;
    @BeforeEach
    public void setup(){
        client = Client.builder().id(1L).name("teste").email("teste@teste.com").build();
    }

    @SneakyThrows
    @Test
    void createSucess(){
        when(clientRepository.create(client)).thenReturn(Client.builder().id(1L).name("teste").email("teste@teste.com").build());
        Client clientCreate = clientService.create(client);
        verify(productListRepository, times(1)).create(ProductList.builder().userId(1L).list(new HashSet<>()).build());
        assertNotNull(clientCreate);
        assertEquals(1L,clientCreate.getId());
    }

    @Test
    @SneakyThrows
    void createClientAlreadyExistsException(){
        when(clientRepository.create(client)).thenThrow(ClientAlreadyExistsException.class);
        assertThrows(ClientAlreadyExistsException.class, () ->{ clientService.create(client);});
    }

    @Test
    @SneakyThrows
    void createClientClientExceptionException(){
        when(clientRepository.create(client)).thenThrow(ClientException.class);
        assertThrows(ClientException.class, () ->{ clientService.create(client);});
    }

    @Test
    @SneakyThrows
    void findById(){
        when(clientRepository.findById(1L)).thenReturn(client);
        Client client = clientService.findById(1L);
        assertEquals("teste",client.getName());
    }

    @Test
    @SneakyThrows
    void findByIdFail(){
        when(clientRepository.findById(1L)).thenReturn(client);
        Client client = clientService.findById(2L);
        assertNull(client);
    }

    @Test
    @SneakyThrows
    void findByIdClientNotExistsException(){
        when(clientRepository.findById(1L)).thenThrow(ClientNotExistsException.class);
        assertThrows(ClientNotExistsException.class, () -> clientService.findById(1L));
    }

    @Test
    @SneakyThrows
    void findByIdClientExceptionException(){
        when(clientRepository.findById(1L)).thenThrow(ClientException.class);
        assertThrows(ClientException.class, () -> clientService.findById(1L));
    }

    @Test
    @SneakyThrows
    void updateById(){
        Client clientUpdateMock = Client.builder().id(1L).name("teste2").email("teste2@teste.com").build();
        when(clientRepository.findById(1L)).thenReturn(client);
        Client clientUpdateResult = clientService.updateById(1L, clientUpdateMock);
        verify(clientRepository, times(1)).update(clientUpdateMock);
        assertEquals(1L, clientUpdateResult.getId());
        assertEquals(clientUpdateMock.getEmail(), clientUpdateResult.getEmail());
        assertNotEquals("teste@teste.com", client.getEmail());
    }

    @Test
    @SneakyThrows
    void updateByIdClientNotExistsException(){
        Client clientUpdateMock = Client.builder().id(1L).name("teste2").email("teste2@teste.com").build();
        when(clientRepository.findById(1L)).thenThrow(ClientNotExistsException.class);
        assertThrows(ClientNotExistsException.class, () ->  clientService.updateById(1L, clientUpdateMock));
    }

    @Test
    @SneakyThrows
    void updateByIdClientException(){
        Client clientUpdateMock = Client.builder().id(1L).name("teste2").email("teste2@teste.com").build();
        when(clientRepository.findById(1L)).thenThrow(ClientException.class);
        assertThrows(ClientException.class, () ->  clientService.updateById(1L, clientUpdateMock));
    }

    @Test
    @SneakyThrows
    void deleteById(){
        when(clientRepository.delete(1L)).thenReturn(true);
        when(productListRepository.deleteByUserId(1L)).thenReturn(true);
        clientService.deleteById(1L);
        verify(clientRepository, times(1)).delete(1L);
        verify(productListRepository,times(1)).deleteByUserId(1L);
    }

    @Test
    @SneakyThrows
    void deleteByIdClientException(){
        when(clientRepository.delete(1L)).thenReturn(false);
        when(productListRepository.deleteByUserId(1L)).thenReturn(true);
        assertThrows(ClientException.class, () -> clientService.deleteById(1L));
        verify(productListRepository,times(0)).deleteByUserId(1L);

    }

}
