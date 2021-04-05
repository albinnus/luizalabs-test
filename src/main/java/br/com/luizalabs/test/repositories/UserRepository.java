package br.com.luizalabs.test.repositories;

import br.com.luizalabs.test.entities.User;
import br.com.luizalabs.test.exceptions.UserException;
import br.com.luizalabs.test.exceptions.UserNotFoundException;

public interface UserRepository {
    User findByEmail(String email) throws UserNotFoundException, UserException;
    User findById(Long id) throws UserNotFoundException, UserException;
    void updateUserToken(String email, String token) throws UserException;}
