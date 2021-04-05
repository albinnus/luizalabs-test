package br.com.luizalabs.test.dtos;

import br.com.luizalabs.test.validators.ValidUuid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductListDto implements Serializable {
    @ValidUuid(message = "Invalid productId")
    private UUID productId;
}
