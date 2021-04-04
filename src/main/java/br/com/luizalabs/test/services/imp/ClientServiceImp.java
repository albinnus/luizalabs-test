package br.com.luizalabs.test.services.imp;

import br.com.luizalabs.test.entities.Client;
import br.com.luizalabs.test.exceptions.ClientAlreadyExistsException;
import br.com.luizalabs.test.exceptions.ClientException;
import br.com.luizalabs.test.exceptions.ClientNotExistsException;
import br.com.luizalabs.test.repositories.ClientRepository;
import br.com.luizalabs.test.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientServiceImp implements ClientService {
    private final ClientRepository clientRepository;

    @Override
    public Client create(Client client) throws ClientAlreadyExistsException, ClientException {
        return clientRepository.create(client);
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
}
