package br.com.luizalabs.test.advices;

import br.com.luizalabs.test.dtos.ApiErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class FieldsExceptionsAdvices {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    ApiErrorDto fieldsExceptionsAdvicesHandle(MethodArgumentNotValidException m) {
        StringBuilder message = new StringBuilder("");
        m.getBindingResult().getFieldErrors().forEach(fieldError ->
                message.append(fieldError.getDefaultMessage()).append(", "));
        message.deleteCharAt(message.length()-2);

        return ApiErrorDto.builder().status(HttpStatus.UNPROCESSABLE_ENTITY).timestamp(LocalDateTime.now()).message(message.toString()).build();
    }
}
