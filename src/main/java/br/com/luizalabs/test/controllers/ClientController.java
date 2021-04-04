package br.com.luizalabs.test.controllers;

import br.com.luizalabs.test.dtos.ClientDto;
import br.com.luizalabs.test.entities.Product;
import br.com.luizalabs.test.entities.ProductList;
import br.com.luizalabs.test.exceptions.*;
import br.com.luizalabs.test.mappers.entities.ClientMapper;
import br.com.luizalabs.test.repositories.ProductListRepository;
import br.com.luizalabs.test.repositories.apis.ProductRepositoryClient;
import br.com.luizalabs.test.services.ClientService;
import br.com.luizalabs.test.services.ProductListService;
import br.com.luizalabs.test.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;
    private final ProductService productService;
    private final ClientMapper clientMapper;

    private final ProductListService productListService;

    @PostMapping(value = "/", consumes = "application/json", produces = "application/json")
    public ClientDto create(@RequestBody ClientDto clientDto) throws ClientAlreadyExistsException, ClientException, ProductListException {
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
    public Product teste() throws ProductNotFoundException {
        HashSet<Product> products = new HashSet<Product>();
      //  products.add(productService.findById(UUID.fromString("1bf0f365-fbdd-4e21-9786-da459d78dd1f")));
       // products.add(productService.findById(UUID.fromString("958ec015-cfcf-258d-c6df-1721de0ab6ea")));
       // ProductList productList = ProductList.builder().userId(2L).list(products).build();
      //  productListRepository.addProduct(productService.findById(UUID.fromString("4bd442b1-4a7d-2475-be97-a7b22a08a024")));
        //productListRepository.save(productList);
        productListService.addProduct(productService.findById(UUID.fromString("958ec015-cfcf-258d-c6df-1721de0ab6ea")),15L);
        return productService.findById(UUID.fromString("1579762e-cfc3-5f20-afb7-8208ea92cdbd1"));
    }
}
