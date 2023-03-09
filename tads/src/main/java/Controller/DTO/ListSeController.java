package Controller.DTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/listse")
@RestController
public class ListSeController {
    @Autowired
    private ListSeController ListSeService;
    @GetMapping
    public ResponseEntity<ReponseDto> getKids(){
        return new ResponseEntity<>(new ReponseDto(
                200,ListSeService.getKids(),null), HttpStatus.OK);
    }


}
