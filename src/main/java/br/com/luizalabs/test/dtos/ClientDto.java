package br.com.luizalabs.test.dtos;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class ClientDto implements Serializable {
    private Long id;
    private String name;

    private String email;
}
