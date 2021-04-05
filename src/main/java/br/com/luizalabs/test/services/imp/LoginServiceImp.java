package br.com.luizalabs.test.services.imp;

import br.com.luizalabs.test.entities.User;
import br.com.luizalabs.test.exceptions.UserException;
import br.com.luizalabs.test.exceptions.UserInvalidCredencialException;
import br.com.luizalabs.test.exceptions.UserNotFoundException;
import br.com.luizalabs.test.services.LoginService;
import br.com.luizalabs.test.services.UserService;
import br.com.luizalabs.test.utils.JwtToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImp implements LoginService {

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final JwtToken jwtToken;

    @Override
    public User auth(String email, String password) throws UserInvalidCredencialException, UserNotFoundException, UserException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            return login(email);
        } catch (DisabledException | BadCredentialsException | AuthenticationCredentialsNotFoundException e){
            throw  new UserInvalidCredencialException("Bad credentials");
        } catch (UsernameNotFoundException u){
            throw new UserNotFoundException("User not found");
        } catch (UserException e) {
            throw  new UserException(e.getMessage());
        }
    }

    private User login(String email) throws UserNotFoundException, UserException {
        User user = userService.getUserByEmail(email);
        userService.updateUserToken(user.getEmail(),jwtToken.generateToken(email));
        return  user;
    }
}
