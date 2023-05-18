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
    @Min(1)
    @Max(14)
    @NotNull
    private char gender;
    @Size(min = 7, max =15 )
    @NotBlank
    private String phone;
    @NotBlank
    @Size(max = 15,min = 1,message = "digite maximo 15 caracteres  y minimo 1 caracter")
    @NotNull(message = "debe llenar este campo")
    private String identification;
    @NotBlank
    @Size(max = 30)
    private String breed;
    @Valid
    @NotNull
    private Location location;

}
