package br.com.luizalabs.test.advices;

import br.com.luizalabs.test.dtos.ApiErrorDto;
import br.com.luizalabs.test.exceptions.ProductListException;
import br.com.luizalabs.test.exceptions.UserInvalidCredencialException;
import br.com.luizalabs.test.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class UserAdvices {
    @ExceptionHandler(UserInvalidCredencialException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    ApiErrorDto userNotFoundAdviceHandle(UserInvalidCredencialException u){
        return ApiErrorDto.builder().status(HttpStatus.UNAUTHORIZED).timestamp(LocalDateTime.now()).message(u.getMessage()).build();
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ApiErrorDto userBadCredencialAdviceHandle(UserNotFoundException u){
        return ApiErrorDto.builder().status(HttpStatus.NOT_FOUND).timestamp(LocalDateTime.now()).message(u.getMessage()).build();
    }
}
