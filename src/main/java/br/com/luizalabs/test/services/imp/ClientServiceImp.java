package br.com.luizalabs.test.services.imp;

import br.com.luizalabs.test.entities.Client;
import br.com.luizalabs.test.entities.ProductList;
import br.com.luizalabs.test.exceptions.ClientAlreadyExistsException;
import br.com.luizalabs.test.exceptions.ClientException;
import br.com.luizalabs.test.exceptions.ClientNotExistsException;
import br.com.luizalabs.test.exceptions.ProductListException;
import br.com.luizalabs.test.repositories.ClientRepository;
import br.com.luizalabs.test.repositories.ProductListRepository;
import br.com.luizalabs.test.services.ClientService;
import br.com.luizalabs.test.services.ProductListService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class ClientServiceImp implements ClientService {
    private final ClientRepository clientRepository;
    private final ProductListRepository productListRepository;

    @Override
    public Client create(Client client) throws ClientAlreadyExistsException, ClientException, ProductListException {
           Client clientCreate = clientRepository.create(client);
           productListRepository.create(productListBuild(clientCreate.getId()));
           return clientCreate;
    }

    @Override
    @Cacheable(cacheNames = "Client", key = "#root.args")
    public Client findById(Long id) throws ClientNotExistsException, ClientException {
        return clientRepository.findById(id);
    }

    @Override
    @CachePut(cacheNames ="Client", key = "#id")
    public Client updateById(Long id, Client client) throws ClientNotExistsException, ClientException {
        Client clientUpdate = findById(id);
        clientUpdate.setEmail(client.getEmail());
        clientUpdate.setName(client.getName());
        clientRepository.update(clientUpdate);
        return clientUpdate;
    }

    @Override
    @Transactional(rollbackOn = ClientException.class)
    @CacheEvict(cacheNames = "Client", key = "#id")
    public void deleteById(Long id) throws ClientException {
        if(!clientRepository.delete(id)){
            throw new ClientException("Error delete cliente");
        }
        productListRepository.deleteByUserId(id);
    }

    private ProductList productListBuild(Long userId){
        return ProductList.builder().userId(userId).list(new HashSet<>()).build();
    }
}
