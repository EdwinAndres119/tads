package Controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ReponseDto {
    private int code;
    private Object data;
    private List<ErrorDto> errors;

}
