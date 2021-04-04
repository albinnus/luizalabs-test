package br.com.luizalabs.test.mappers.repositories;

import br.com.luizalabs.test.entities.Client;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientRepositoryMapper implements RowMapper<Client> {
    @Override
    public Client mapRow(ResultSet resultSet, int i) throws SQLException {
        return Client.builder()
                .id(resultSet.getLong("id"))
                .email(resultSet.getString("email"))
                .name(resultSet.getString("name")).build();
    }
}
