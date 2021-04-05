package br.com.luizalabs.test.dtos;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class BearerDto implements Serializable {
    private String token;
}
