package br.com.luizalabs.test.properties;

import br.com.luizalabs.test.factory.YamlPropertySourceFactory;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "client")
@PropertySource(value = "classpath:queries/client.yml", factory = YamlPropertySourceFactory.class)
@Data
public class ClientQueries {
    private String insertClient;
    private String selectClientById;
    private String updateClientById;
    private String deleteClientById;
}
