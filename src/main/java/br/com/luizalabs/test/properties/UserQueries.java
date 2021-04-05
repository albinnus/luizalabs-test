package br.com.luizalabs.test.properties;

import br.com.luizalabs.test.factory.YamlPropertySourceFactory;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "user")
@PropertySource(value = "classpath:queries/user.yml", factory = YamlPropertySourceFactory.class)
@Data
public class UserQueries {
    private String updateUserToken;
    private String selectUserId;
    private String selectUserEmail;
    private String selectUserAuth;
    private String selectUserAuthority;
}
