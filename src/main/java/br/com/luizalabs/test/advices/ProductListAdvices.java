package br.com.luizalabs.test.advices;

import br.com.luizalabs.test.dtos.ApiErrorDto;
import br.com.luizalabs.test.exceptions.ProductListException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ProductListAdvices {
    @ExceptionHandler(ProductListException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ApiErrorDto productListErrorAdviceHandle(ProductListException p){
        return ApiErrorDto.builder().status(HttpStatus.NOT_FOUND).timestamp(LocalDateTime.now()).message(p.getMessage()).build();
    }
}
