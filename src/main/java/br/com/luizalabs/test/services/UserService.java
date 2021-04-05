package br.com.luizalabs.test.services;

import br.com.luizalabs.test.entities.User;
import br.com.luizalabs.test.exceptions.UserException;
import br.com.luizalabs.test.exceptions.UserNotFoundException;


public interface UserService {

    void updateUserToken(String email, String token) throws UserException;

    User getUserByEmail(String email) throws UserNotFoundException, UserException;
}
