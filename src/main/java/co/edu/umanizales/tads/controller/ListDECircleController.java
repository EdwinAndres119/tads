package co.edu.umanizales.tads.controller;


import co.edu.umanizales.tads.controller.dto.PetDTO;
import co.edu.umanizales.tads.controller.dto.ResponseDTO;
import co.edu.umanizales.tads.exception.ListDeException;
import co.edu.umanizales.tads.exception.RequestException;
import co.edu.umanizales.tads.model.Location;
import co.edu.umanizales.tads.model.Pet;
import co.edu.umanizales.tads.service.ListDECircleService;
import co.edu.umanizales.tads.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
@RestController
@RequestMapping(path = "listCircle_de")
public class ListDECircleController {
    @Autowired
    private ListDECircleService listDECircleService;
    @Autowired
    private LocationService locationService;
    @GetMapping(path = "/get_list")
    public ResponseEntity<ResponseDTO> getPets() {
        return new ResponseEntity<>(new ResponseDTO(
                200, listDECircleService.showList(), null), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<ResponseDTO> addPet(@Valid @RequestBody PetDTO petDTO) throws ListDeException {
        try {
            Location location = locationService.getLocationByCode(petDTO.getCodeLocation());
            if (location == null) {
                return new ResponseEntity<>(new ResponseDTO(
                        404, "La ubicación no existe",
                        null), HttpStatus.BAD_REQUEST);
            }
            listDECircleService.addPetToEnd(
                    new Pet(petDTO.getAge(),
                            petDTO.getName(), petDTO.getBreed(),
                            petDTO.getGender(), petDTO.getIdentification(), location,petDTO.isShower()));
            return new ResponseEntity<>(new ResponseDTO(
                    200, "Se ha adicionado el petacón",
                    null), HttpStatus.OK);

        } catch (ListDeException e) {
            throw new RequestException(e.getCode(), e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping(path = "/add_pet_to_beginning")
    public ResponseEntity<ResponseDTO> addPetToBeginning(@Valid @RequestBody PetDTO petDTO) throws ListDeException {
        try{
            Location location = locationService.getLocationByCode(petDTO.getCodeLocation());
            if (location == null) {
                return new ResponseEntity<>(new ResponseDTO(
                        404, "La ubicación no existe",
                        null), HttpStatus.OK);
            }
            listDECircleService.addPetToBeginning(
                    new Pet(petDTO.getAge(),
                            petDTO.getName(), petDTO.getBreed(),
                            petDTO.getGender(), petDTO.getIdentification(), location,petDTO.isShower()));
            return new ResponseEntity<>(new ResponseDTO(
                    200, "Se ha adicionado el petacón",
                    null), HttpStatus.OK);
        } catch (ListDeException e){
            throw new RequestException(e.getCode(),e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping(path = "/add_pet_in_pos/{pos}")
    public ResponseEntity<ResponseDTO> addInPos(@Valid @RequestBody PetDTO petDTO, @Min(0) @PathVariable int pos) {
        try{
            Location location = locationService.getLocationByCode(petDTO.getCodeLocation());
            if (location == null) {
                return new ResponseEntity<>(new ResponseDTO(
                        404, "La ubicación no existe",
                        null), HttpStatus.BAD_REQUEST);
            }
            listDECircleService.addInPos(new Pet(petDTO.getAge(), petDTO.getName(), petDTO.getBreed(), petDTO.getGender(), petDTO.getIdentification(),
                    location, petDTO.isShower()),pos);
            return new ResponseEntity<>(new ResponseDTO(
                    200, "Se ha adicionado el petacón",
                    null), HttpStatus.OK);
        } catch (ListDeException e){
            throw new RequestException(e.getCode(),e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping (path = "/clean_pet")
    public ResponseEntity<ResponseDTO> cleanPet(@RequestBody char direction){
        try{
            listDECircleService.cleanPet(direction);
            return new ResponseEntity<>(new ResponseDTO(200,"La mascota ha sido bañada",null),HttpStatus.OK);
        }catch(ListDeException e){
            throw new RequestException(e.getCode(),e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}

