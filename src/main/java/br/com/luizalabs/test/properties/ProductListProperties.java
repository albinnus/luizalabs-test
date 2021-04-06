package br.com.luizalabs.test.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "product-list")
@Data
public class ProductListProperties {
    private Integer sizePagination;
}
