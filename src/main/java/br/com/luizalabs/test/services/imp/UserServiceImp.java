package br.com.luizalabs.test.services.imp;

import br.com.luizalabs.test.entities.User;
import br.com.luizalabs.test.exceptions.UserException;
import br.com.luizalabs.test.exceptions.UserNotFoundException;
import br.com.luizalabs.test.repositories.UserRepository;
import br.com.luizalabs.test.services.UserService;
import br.com.luizalabs.test.utils.JwtToken;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    @Override
    @CacheEvict(cacheNames = "UserByEmail", key = "#email")
    public void updateUserToken(String email, String token) throws UserException {
         userRepository.updateUserToken(email, token);
    }

    @Override
    @Cacheable(cacheNames = "UserByEmail", key = "#email")
    public User getUserByEmail(String email) throws UserNotFoundException, UserException {
        return userRepository.findByEmail(email);
    }
}
