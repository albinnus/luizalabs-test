package br.com.luizalabs.test.advices;

import br.com.luizalabs.test.dtos.ApiErrorDto;
import br.com.luizalabs.test.exceptions.ClientAlreadyExistsException;
import br.com.luizalabs.test.exceptions.ClientException;
import br.com.luizalabs.test.exceptions.ClientNotExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ClientAdvices {

    @ExceptionHandler(ClientException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ApiErrorDto clientErrorAdviceHandle(ClientException c){
        return ApiErrorDto.builder().status(HttpStatus.BAD_REQUEST).timestamp(LocalDateTime.now()).message(c.getMessage()).build();
    }

    @ExceptionHandler(ClientAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ApiErrorDto clientAlreadyExistsAdviceHandle(ClientAlreadyExistsException c){
        return ApiErrorDto.builder().status(HttpStatus.BAD_REQUEST).timestamp(LocalDateTime.now()).message(c.getMessage()).build();
    }

    @ExceptionHandler(ClientNotExistsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ApiErrorDto clientNotExistsAdviceHandle(ClientAlreadyExistsException c){
        return ApiErrorDto.builder().status(HttpStatus.NOT_FOUND).timestamp(LocalDateTime.now()).message(c.getMessage()).build();
    }
}
