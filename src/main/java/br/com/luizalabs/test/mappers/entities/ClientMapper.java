package br.com.luizalabs.test.mappers.entities;

import br.com.luizalabs.test.dtos.ClientDto;
import br.com.luizalabs.test.entities.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
    public Client toEntity(ClientDto clientDto){
        return Client.builder().name(clientDto.getName()).email(clientDto.getEmail()).build();
    }

    public ClientDto toDto(Client client){
        return ClientDto.builder().id(client.getId()).name(client.getName()).email(client.getEmail()).build();
    }
}
