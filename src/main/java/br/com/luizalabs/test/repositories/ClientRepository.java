package br.com.luizalabs.test.repositories;

import br.com.luizalabs.test.entities.Client;
import br.com.luizalabs.test.exceptions.ClientAlreadyExistsException;
import br.com.luizalabs.test.exceptions.ClientException;
import br.com.luizalabs.test.exceptions.ClientNotExistsException;

public interface ClientRepository {
    public Client create(Client client) throws ClientAlreadyExistsException, ClientException;
    public Client findById(Long id) throws ClientNotExistsException, ClientException;
    public void update(Client client) throws ClientException;

}
