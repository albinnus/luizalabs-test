package br.com.luizalabs.test.entities;


import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import java.util.HashSet;

@Data
@Builder
public class ProductList {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String uuid;
    private Long userId;
    private HashSet<Product> list;
}
