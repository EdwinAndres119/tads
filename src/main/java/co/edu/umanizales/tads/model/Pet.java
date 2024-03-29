package co.edu.umanizales.tads.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor

public class Pet{
    @Positive
    @NotNull
    private byte age ;
    @NotBlank
    @Size(max =30)
    private String name;
    @NotBlank
    @Size(max = 30)
    private String breed;
    @Min(1)
    @Max(14)
    @NotNull
    private char gender;
    @NotBlank
    @Size(max = 15,min = 1,message = "digite maximo 15 caracteres  y minimo 1 caracter")
    @NotNull(message = "debe llenar este campo")
    private String identification;

    @Valid
    @NotNull
    private Location location;

    private boolean shower;

}
