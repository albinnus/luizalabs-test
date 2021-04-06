package br.com.luizalabs.test.dtos;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class AuthDto implements Serializable {
    @NotBlank(message = "The email can not be blank")
    @Email(message = "The email is invalid")
    private String email;

    @NotBlank(message = "The password can not be blank")
    private String password;
}
