package co.edu.umanizales.tads.controller.dto;

import lombok.Data;

@Data
public class ErrorDTO {
    private int code;
    private String message;
    public ErrorDTO(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
