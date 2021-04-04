package br.com.luizalabs.test.entities;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Client implements Serializable {
    private Long id;
    private String name;
    private String email;
}
