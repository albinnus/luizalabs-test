package br.com.luizalabs.test.repositories.imp;

import br.com.luizalabs.test.entities.User;
import br.com.luizalabs.test.exceptions.UserException;
import br.com.luizalabs.test.exceptions.UserNotFoundException;
import br.com.luizalabs.test.mappers.repositories.UserRepositoryMapper;
import br.com.luizalabs.test.properties.UserQueries;
import br.com.luizalabs.test.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImp implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    private final UserQueries userQueries;

    @Override
    public User findByEmail(String email) throws UserNotFoundException, UserException {
        try {
            return jdbcTemplate.queryForObject(userQueries.getSelectUserEmail(), new UserRepositoryMapper(), email);
        }catch (EmptyResultDataAccessException e){
            throw new UserNotFoundException("User with email "+email+" not found");
        }catch (Exception e){
            throw  new UserException(e.getMessage());
        }
    }

    @Override
    public User findById(Long id) throws UserNotFoundException, UserException {
        try {
            return jdbcTemplate.queryForObject(userQueries.getSelectUserId(), new UserRepositoryMapper(), id);
        }catch (EmptyResultDataAccessException e){
            throw new UserNotFoundException("User with id "+id+" not found");
        }catch (Exception e){
            throw  new UserException(e.getMessage());
        }
    }

    @Override
    public void updateUserToken(String email, String token) throws UserException {
        try {
            jdbcTemplate.update( connection -> {
                PreparedStatement ps = connection.prepareStatement(userQueries.getUpdateUserToken());
                ps.setString(1, token);
                ps.setString(2, email);
                return ps;
            });
        }catch (Exception e){
            throw  new UserException(e.getMessage());
        }
    }


}
