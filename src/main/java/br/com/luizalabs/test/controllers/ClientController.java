package br.com.luizalabs.test.controllers;

import br.com.luizalabs.test.dtos.ClientDto;
import br.com.luizalabs.test.dtos.ProductListDto;
import br.com.luizalabs.test.dtos.ProductListPaginationDto;
import br.com.luizalabs.test.exceptions.*;
import br.com.luizalabs.test.mappers.entities.ClientMapper;
import br.com.luizalabs.test.mappers.entities.ProductListMapper;
import br.com.luizalabs.test.services.ClientService;
import br.com.luizalabs.test.services.ProductListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;
    private final ClientMapper clientMapper;
    private final ProductListMapper productListMapper;
    private final ProductListService productListService;

    @PostMapping(value = "/", consumes = "application/json", produces = "application/json")
    public ClientDto create(@Valid @RequestBody ClientDto clientDto) throws ClientAlreadyExistsException, ClientException, ProductListException {
        return  clientMapper.toDto(clientService.create(clientMapper.toEntity(clientDto)));
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ClientDto findById(@PathVariable("id") long id) throws ClientNotExistsException, ClientException {
        return  clientMapper.toDto(clientService.findById(id));
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    public ClientDto updateById(@PathVariable("id") long id, @Valid @RequestBody ClientDto clientDto) throws ClientNotExistsException, ClientException, ClientAlreadyExistsException {
        return  clientMapper.toDto(clientService.updateById(id, clientMapper.toEntity(clientDto)));
    }


    @GetMapping("/{id}/products")
    public ProductListPaginationDto getProducts(@PathVariable("id") Long userId, @RequestParam Optional<Integer> page) throws ClientNotExistsException, ClientException {
       return  productListMapper.toPaginationDto(productListService.productList(userId, page.orElseGet(()->1)));
    }

    @PostMapping("/{id}/products")
    public void addProduct(@PathVariable("id") Long userId,@Valid @RequestBody ProductListDto productListDto) throws ProductNotFoundException, ClientNotExistsException, ClientException {
       productListService.addProduct(productListDto.getProductId(), userId);
    }

    @DeleteMapping("/{id}/products")
    public void deleteProduct(@PathVariable("id") Long userId,@Valid @RequestBody ProductListDto productListDto) throws ProductNotFoundException, ProductListRemoveException, ClientNotExistsException, ClientException {
        productListService.deleteProductInList(productListDto.getProductId(), userId);
    }
        @DeleteMapping("/{id}")
    public void deleteClientById(@PathVariable("id") Long userId) throws ClientException, ClientNotExistsException {
        clientService.deleteById(userId);
    }
}
