package br.com.luizalabs.test.services;

import br.com.luizalabs.test.entities.User;
import br.com.luizalabs.test.exceptions.UserException;
import br.com.luizalabs.test.exceptions.UserNotFoundException;
import br.com.luizalabs.test.repositories.UserRepository;
import br.com.luizalabs.test.repositories.imp.UserRepositoryImp;
import br.com.luizalabs.test.services.imp.UserServiceImp;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    private final UserRepository userRepository = mock(UserRepositoryImp.class);
    private final UserService userService = new UserServiceImp(userRepository);
    private User user;
    private final JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);

    @BeforeEach
    public void setup(){
        user = User.builder().id(1L).email("teste@teste.com").build();
    }

    @Test
    @SneakyThrows
    void getUserByEmail(){
        when(userRepository.findByEmail("teste@teste.com")).thenReturn(user);
        User userFind = userService.getUserByEmail("teste@teste.com");
        verify(userRepository, times(1)).findByEmail("teste@teste.com");
        assertEquals(user.getEmail(), userFind.getEmail());
    }

    @Test
    @SneakyThrows
    void getUserByEmailUserNotFoundException(){
        when(userRepository.findByEmail("teste@teste.com")).thenThrow(UserNotFoundException.class);
        assertThrows(UserNotFoundException.class, () -> userService.getUserByEmail("teste@teste.com"));
        verify(userRepository, times(1)).findByEmail("teste@teste.com");
    }

    @Test
    @SneakyThrows
    void getUserByEmailUserException(){
        when(userRepository.findByEmail("teste@teste.com")).thenThrow(UserException.class);
        assertThrows(UserException.class, () -> userService.getUserByEmail("teste@teste.com"));
        verify(userRepository, times(1)).findByEmail("teste@teste.com");
    }

    @Test
    @SneakyThrows
    void updateUserToken(){
        userService.updateUserToken("teste","teste");
        verify(userRepository, times(1) ).updateUserToken("teste","teste");
    }

}
