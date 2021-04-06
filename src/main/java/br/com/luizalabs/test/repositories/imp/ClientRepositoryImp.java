package br.com.luizalabs.test.repositories.imp;

import br.com.luizalabs.test.entities.Client;
import br.com.luizalabs.test.exceptions.ClientAlreadyExistsException;
import br.com.luizalabs.test.exceptions.ClientException;
import br.com.luizalabs.test.exceptions.ClientNotExistsException;
import br.com.luizalabs.test.mappers.repositories.ClientRepositoryMapper;
import br.com.luizalabs.test.properties.ClientQueries;
import br.com.luizalabs.test.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class ClientRepositoryImp implements ClientRepository {

    private final JdbcTemplate jdbcTemplate;

    private final ClientQueries clientQueries;

    @Override
    public Client create(Client client) throws ClientAlreadyExistsException, ClientException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update( connection -> {
                PreparedStatement ps = connection.prepareStatement(clientQueries.getInsertClient(), Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, client.getName());
                ps.setString(2, client.getEmail());
                return ps;
            }, keyHolder);
            client.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
            return client;
        }catch (DuplicateKeyException sqlException){
            throw  new ClientAlreadyExistsException(client.getEmail());
        } catch (Exception e){
            throw  new ClientException(e.getMessage());
        }


    }

    @Override
    public Client findById(Long id) throws ClientNotExistsException, ClientException {
        try {
            return jdbcTemplate.queryForObject(clientQueries.getSelectClientById(), new ClientRepositoryMapper(), id);
        }catch (EmptyResultDataAccessException e){
            throw new ClientNotExistsException(id);
        }catch (Exception e){
            throw  new ClientException(e.getMessage());
        }
    }

    @Override
    public void update(Client client) throws ClientException, ClientAlreadyExistsException {
        try {
            jdbcTemplate.update( connection -> {
                PreparedStatement ps = connection.prepareStatement(clientQueries.getUpdateClientById(), Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, client.getName());
                ps.setString(2, client.getEmail());
                ps.setLong(3, client.getId());
                return ps;
            });
        }catch (DuplicateKeyException d){
            throw  new ClientAlreadyExistsException(client.getEmail());
        } catch(Exception e){
            throw  new ClientException(e.getMessage());
        }

    }

    @Override
    public boolean delete(Long id) throws ClientException {
        try {
            int result = jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(clientQueries.getDeleteClientById());
                ps.setLong(1, id);
                return ps;
            });
            return result == 1;
        }catch (Exception e){
            throw  new ClientException(e.getMessage());
        }
    }
}
