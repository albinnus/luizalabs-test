package br.com.luizalabs.test.mappers.entities;

import br.com.luizalabs.test.dtos.BearerDto;
import br.com.luizalabs.test.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public BearerDto toBearerDto(User user){
        return BearerDto.builder().token(user.getToken()).build();
    }
}
