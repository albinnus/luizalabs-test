package br.com.luizalabs.test.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthDto implements Serializable {
    private String email;
    private String password;
}
