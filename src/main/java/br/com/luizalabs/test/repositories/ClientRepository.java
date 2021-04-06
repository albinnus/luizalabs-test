package br.com.luizalabs.test.repositories;

import br.com.luizalabs.test.entities.Client;
import br.com.luizalabs.test.exceptions.ClientAlreadyExistsException;
import br.com.luizalabs.test.exceptions.ClientException;
import br.com.luizalabs.test.exceptions.ClientNotExistsException;

public interface ClientRepository {
    Client create(Client client) throws ClientAlreadyExistsException, ClientException;
    Client findById(Long id) throws ClientNotExistsException, ClientException;
    void update(Client client) throws ClientException;
    boolean delete(Long id) throws ClientException;


}
