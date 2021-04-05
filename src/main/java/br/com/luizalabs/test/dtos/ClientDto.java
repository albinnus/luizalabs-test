package br.com.luizalabs.test.dtos;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Builder
@Data
public class ClientDto implements Serializable {

    private Long id;

    @NotBlank(message = "The name can not be blank")
    private String name;

    @Email(message = "The email is invalid")
    private String email;
}
