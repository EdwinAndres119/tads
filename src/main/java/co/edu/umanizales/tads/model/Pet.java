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
    @Size(min = 6, max =15 )
    @NotBlank
    private String ownernumb;
    @NotBlank
    @Size(max = 30)
    private String breed;
    @Valid
    @NotNull
    private Location location;

}
