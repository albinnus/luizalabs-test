package br.com.luizalabs.test.entities;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class User implements Serializable {
    private Long id;
    private String email;
    private String password;
    private String token;
}
