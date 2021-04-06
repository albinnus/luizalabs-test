package br.com.luizalabs.test.advices;

import br.com.luizalabs.test.dtos.ApiErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class MethodArgumentTypeMismatchExceptionAdvices {
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ApiErrorDto clientErrorAdviceHandle(MethodArgumentTypeMismatchException m){
        return ApiErrorDto.builder().status(HttpStatus.BAD_REQUEST).timestamp(LocalDateTime.now()).message("The parameter type passed is invalid").build();
    }

}
