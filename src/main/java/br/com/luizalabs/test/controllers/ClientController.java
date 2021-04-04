package br.com.luizalabs.test.controllers;

import br.com.luizalabs.test.dtos.ClientDto;
import br.com.luizalabs.test.entities.Product;
import br.com.luizalabs.test.exceptions.ClientAlreadyExistsException;
import br.com.luizalabs.test.exceptions.ClientException;
import br.com.luizalabs.test.exceptions.ClientNotExistsException;
import br.com.luizalabs.test.mappers.entities.ClientMapper;
import br.com.luizalabs.test.repositories.apis.ProductRepository;
import br.com.luizalabs.test.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;
    private final ProductRepository productRepository;
    private final ClientMapper clientMapper;

    @PostMapping(value = "/", consumes = "application/json", produces = "application/json")
    public ClientDto create(@RequestBody ClientDto clientDto) throws ClientAlreadyExistsException, ClientException {
        return  clientMapper.toDto(clientService.create(clientMapper.toEntity(clientDto)));
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ClientDto findById(@PathVariable("id") long id) throws ClientNotExistsException, ClientException {
        return  clientMapper.toDto(clientService.findById(id));
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    public ClientDto updateById(@PathVariable("id") long id, @RequestBody ClientDto clientDto) throws ClientNotExistsException, ClientException {
        return  clientMapper.toDto(clientService.updateById(id, clientMapper.toEntity(clientDto)));
    }

    @GetMapping("/test")
    public Product teste(){

        return productRepository.getProductById(UUID.fromString("1579762e-cfc3-5f20-afb7-8208ea92cbd1"));
    }
}
