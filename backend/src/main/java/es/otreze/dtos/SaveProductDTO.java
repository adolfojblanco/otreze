package es.otreze.dtos;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveProductDTO implements Serializable {


    @NotBlank
    private String name;
    @DecimalMin(value = "0.01")
    private BigDecimal price;

    private UUID categoryId;

    private Integer stock;

    private Boolean hasStock;

}
