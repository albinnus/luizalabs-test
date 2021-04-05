package br.com.luizalabs.test.services;

import br.com.luizalabs.test.entities.User;
import br.com.luizalabs.test.exceptions.UserException;
import br.com.luizalabs.test.exceptions.UserInvalidCredencialException;
import br.com.luizalabs.test.exceptions.UserNotFoundException;

public interface LoginService {
    User auth(String email, String password) throws UserInvalidCredencialException, UserNotFoundException, UserException;
}
