package br.com.luizalabs.test.controllers;

import br.com.luizalabs.test.dtos.AuthDto;
import br.com.luizalabs.test.dtos.BearerDto;
import br.com.luizalabs.test.exceptions.UserException;
import br.com.luizalabs.test.exceptions.UserInvalidCredencialException;
import br.com.luizalabs.test.exceptions.UserNotFoundException;
import br.com.luizalabs.test.mappers.entities.UserMapper;
import br.com.luizalabs.test.services.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    private final UserMapper userMapper;

    @PostMapping(value = "/login",consumes = "application/json", produces = "application/json")
    public BearerDto login(@RequestBody AuthDto authRequest) throws UserInvalidCredencialException, UserNotFoundException, UserException {
        return userMapper.toBearerDto(loginService.auth(authRequest.getEmail(), authRequest.getPassword()));
    }
}
