package br.com.luizalabs.test.services;

import br.com.luizalabs.test.entities.Client;
import br.com.luizalabs.test.exceptions.ClientAlreadyExistsException;
import br.com.luizalabs.test.exceptions.ClientException;
import br.com.luizalabs.test.exceptions.ClientNotExistsException;

public interface ClientService {
    Client create(Client client) throws ClientAlreadyExistsException, ClientException;
    Client findById(Long id) throws ClientNotExistsException, ClientException;
    Client updateById(Long id, Client client) throws ClientNotExistsException, ClientException;
}
