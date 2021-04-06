package br.com.luizalabs.test.services;

import br.com.luizalabs.test.entities.User;
import br.com.luizalabs.test.exceptions.UserException;
import br.com.luizalabs.test.exceptions.UserInvalidCredencialException;
import br.com.luizalabs.test.exceptions.UserNotFoundException;
import br.com.luizalabs.test.services.imp.LoginServiceImp;
import br.com.luizalabs.test.utils.JwtToken;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class LoginServiceTest {
    private final UserService userService = mock(UserService.class);

    private final AuthenticationManager authenticationManager = mock(AuthenticationManager.class);

    private final JwtToken jwtToken = mock(JwtToken.class);

    private final LoginService loginService = new LoginServiceImp(userService,authenticationManager, jwtToken);

    private  User user;

    @BeforeEach
    void setup(){
        user = User.builder().id(1L).email("teste@teste.com").build();
        when(jwtToken.generateToken(user.getEmail())).thenReturn("e");
    }

    @Test
    @SneakyThrows
    public void auth(){
        when(userService.getUserByEmail("teste@teste.com")).thenReturn(user);
        User userMock = loginService.auth("teste@teste.com","teste");
        verify(userService, times(1)).updateUserToken("teste@teste.com","e");
        assertEquals("teste@teste.com",userMock.getEmail());
    }

    @Test
    @SneakyThrows
    public void authUserNotFoundException(){
        when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()))).thenThrow(UsernameNotFoundException.class);
        assertThrows(UserNotFoundException.class, ()-> loginService.auth(user.getEmail(), user.getPassword()));
    }

    @Test
    @SneakyThrows
    public void authDisabledException(){
        when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()))).thenThrow(DisabledException.class);
        assertThrows(UserInvalidCredencialException.class, ()-> loginService.auth(user.getEmail(), user.getPassword()));
    }

    @Test
    @SneakyThrows
    public void authAuthenticationCredentialsNotFoundException(){
        when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()))).thenThrow(AuthenticationCredentialsNotFoundException.class);
        assertThrows(UserInvalidCredencialException.class, ()-> loginService.auth(user.getEmail(), user.getPassword()));
    }

    @Test
    @SneakyThrows
    public void authBadCredentialsException(){
        when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()))).thenThrow(BadCredentialsException.class);
        assertThrows(UserInvalidCredencialException.class, ()-> loginService.auth(user.getEmail(), user.getPassword()));
    }

    @Test
    @SneakyThrows
    public void authUserException(){
        when(userService.getUserByEmail(user.getEmail())).thenThrow(UserException.class);
        assertThrows(UserException.class, ()-> loginService.auth(user.getEmail(), user.getPassword()));
    }

}
