package co.edu.umanizales.tads.controller.dto;

import lombok.Data;

@Data
public class PetDTO {
    private byte age;
    private String name;
    private String breed;
    private char gender;
    private String identification;
    private String codeLocation;
    private boolean shower;
}
