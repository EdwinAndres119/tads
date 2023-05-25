package co.edu.umanizales.tads.controller;

import co.edu.umanizales.tads.controller.dto.ErrorDTO;
import co.edu.umanizales.tads.controller.dto.PetByLocationDTO;
import co.edu.umanizales.tads.controller.dto.PetDTO;
import co.edu.umanizales.tads.controller.dto.ResponseDTO;
import co.edu.umanizales.tads.exception.ListDeException;
import co.edu.umanizales.tads.exception.ListSEException;
import co.edu.umanizales.tads.exception.RequestException;
import co.edu.umanizales.tads.model.Location;
import co.edu.umanizales.tads.model.Pet;
import co.edu.umanizales.tads.service.ListDEservice;
import co.edu.umanizales.tads.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping(path = "list_de")
public class ListDEcontroller {
    @Autowired
    private ListDEservice lisDEService;
    @Autowired
    private LocationService LocationService;

    @GetMapping(path = "/get_list")
    public ResponseEntity<ResponseDTO> getPets() {
        return new ResponseEntity<>(new ResponseDTO(
                200, lisDEService.showList(), null), HttpStatus.OK);
    }


    @GetMapping("/invert")
    public ResponseEntity<ResponseDTO> invert() throws ListDeException{
        try {
            lisDEService.invertList();
            return new ResponseEntity<>(new ResponseDTO(
                    200, "SE ha invertido la lista",
                    null), HttpStatus.OK);
        }catch (ListDeException e){
            throw new RequestException(e.getCode(),e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/change_extremes")
    public ResponseEntity<ResponseDTO> changeExtremes() {
            lisDEService.changeExtremes();
            return new ResponseEntity<>(new ResponseDTO(
                    200, "se han intercambiado los extremos", null), HttpStatus.OK);
        }


    @PostMapping
    public ResponseEntity<ResponseDTO> addPet(@Valid @RequestBody PetDTO petDTO) throws ListDeException {
        try {
            Location location = LocationService.getLocationByCode(petDTO.getCodeLocation());
            if (location == null) {
                return new ResponseEntity<>(new ResponseDTO(
                        404, "La ubicación no existe",
                        null), HttpStatus.BAD_REQUEST);
            }
            lisDEService.addPet(
                    new Pet(petDTO.getAge(),
                            petDTO.getName(), petDTO.getBreed(),
                            petDTO.getGender(), petDTO.getIdentification(), location, petDTO.isShower()));
            return new ResponseEntity<>(new ResponseDTO(
                    200, "Se ha adicionado el petacón",
                    null), HttpStatus.OK);

        } catch (ListDeException e) {
            throw new RequestException(e.getCode(), e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/delete_pet_DE/{phone}")
    public ResponseEntity<ResponseDTO> deletePet(@PathVariable String phone) throws ListDeException {
        try {
            lisDEService.deletePet(phone);
            return new ResponseEntity<>(new ResponseDTO(
                    200, "Mascota eliminada", null), HttpStatus.OK);
        } catch (ListDeException e) {
            throw new RequestException(e.getCode(), e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/lose_positions")
    public ResponseEntity<ResponseDTO> losePositions(@RequestBody Map<String, Object> requestBody) throws ListDeException {
        try {
            String id = (String) requestBody.get("id");
            Integer lose = (Integer) requestBody.get("lose");
            lisDEService.losePositions(id, lose);
            return new ResponseEntity<>(new ResponseDTO(
                    200, "posiciones reordenadas", null), HttpStatus.OK);
        } catch (ListDeException e) {
            throw new RequestException(e.getCode(), e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/orderByGender_DE")
    public ResponseEntity<ResponseDTO> orderByGender() throws ListDeException{
        try {


            lisDEService.orderByGender();
            return new ResponseEntity<>(new ResponseDTO(
                    200, "los mascotas fueron ordenados", null), HttpStatus.OK);
        }catch (ListDeException e){
            throw new RequestException(e.getCode(),e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/put_pets_beginning_DE")
    public ResponseEntity<ResponseDTO> putPetBeginning() throws ListDeException{
        try {
            lisDEService.putPetsMaleToBeginning();
            return new ResponseEntity<>(new ResponseDTO(
                    200, "mascotas agregadas al inicio y perras agregadas al final", null), HttpStatus.OK);
        }catch(ListDeException e){
            throw new ListDeException(e.getCode(),e.getMessage());
        }

    }

    @PostMapping(path = "/earn_positions")
    public ResponseEntity<ResponseDTO> earnPositions(@RequestBody Map<String, Object> requestBody)throws ListDeException {
        try {
            String id = (String) requestBody.get("id");
            Integer earn = (Integer) requestBody.get("earn");
            lisDEService.earnPositions(id, earn);
            return new ResponseEntity<>(new ResponseDTO(
                    200, "posiciones re ordenadas", null), HttpStatus.OK);
        }catch (ListDeException e){
            throw new RequestException(e.getCode(),e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/add_pet_to_beginning")
    public ResponseEntity<ResponseDTO> addPetToBeginning(@Valid @RequestBody PetDTO petDTO) throws ListDeException {
        try{
            Location location = LocationService.getLocationByCode(petDTO.getCodeLocation());
            if (location == null) {
                return new ResponseEntity<>(new ResponseDTO(
                        404, "La ubicación no existe",
                        null), HttpStatus.OK);
            }
            lisDEService.addPetToBeginning(
                    new Pet(petDTO.getAge(),
                            petDTO.getName(), petDTO.getBreed(),
                            petDTO.getGender(), petDTO.getIdentification(), location, petDTO.isShower()));
            return new ResponseEntity<>(new ResponseDTO(
                    200, "Se ha adicionado el petacón",
                    null), HttpStatus.OK);
        } catch (ListDeException e){
            throw new RequestException(e.getCode(),e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/get_average_age_DE")
    public ResponseEntity<ResponseDTO> getHalfAgeDog() throws ListDeException {
        try {
            lisDEService.getHalfAgeDog();
            return new ResponseEntity<>(new ResponseDTO(
                    200, "el promedio de edad de las mascotas es: " + lisDEService.getHalfAgeDog(), null), HttpStatus.OK);
        }catch (ListDeException e){
            throw new RequestException(e.getCode(),e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/send_pets_to_end_by_char")
    public ResponseEntity<ResponseDTO> sendPetsToEndByChar(@RequestBody Map<String, Object> requestData) throws ListDeException {
        try {
            String userString = (String) requestData.get("user");
            char user = userString.toLowerCase().charAt(0);
            lisDEService.sendPetsToEndByChar(user);
            return new ResponseEntity<>(new ResponseDTO(
                    200, "mascotas ordenadas", null), HttpStatus.OK);
        } catch (ListDeException e) {
            throw new RequestException(e.getCode(), e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/generate_report_by_age_DE")
    public ResponseEntity<ResponseDTO> ReportByage() throws ListDeException{
        try {
            return new ResponseEntity<>(new ResponseDTO(200, lisDEService.ReportByage(), null), HttpStatus.OK);
        }catch (ListDeException e){
            throw new RequestException(e.getCode(),e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/delete_Pet_DE/{id}")
    public ResponseEntity<ResponseDTO> RemovePetInPosition(@PathVariable String id, @RequestParam int pos1) throws ListDeException {
        try {
            lisDEService.RemovePetInPosition(id, pos1);
        } catch (ListSEException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(new ResponseDTO(200, "Mascota eliminada", null), HttpStatus.OK);
    }
    @GetMapping(path = "/petsbyDepartmentDE/{age}")
    public ResponseEntity<ResponseDTO> getPetsByDepartment(@PathVariable byte age) {
        List<PetByLocationDTO> petByLocationDTO = new ArrayList<>();
        for (Location loc : LocationService.getLocationsByCodeSize(5)) {
            if (lisDEService.getPets().getData().getAge() > age) {
                int count = lisDEService.getCounPetLocCode(loc.getCode());
                int male = lisDEService.getCountPetByLocCodeMale(loc.getCode());
                int female = lisDEService.getCountPetByLocCodeFemale(loc.getCode());
                if (count > 0) {
                    petByLocationDTO.add(new PetByLocationDTO(loc, female, male, count));
                }
            }
        }
        return new ResponseEntity<>(new ResponseDTO(
                200, petByLocationDTO,
                null), HttpStatus.OK);
    }

    @GetMapping(path = "/pets_by_locations_DE/{age}")
    public ResponseEntity<ResponseDTO> getKidsByLocation(@PathVariable byte age) {
        List<PetByLocationDTO> petByLocationDTOS = new ArrayList<>();
        for (Location loc : LocationService.getLocations()) {
            if (lisDEService.getPets().getData().getAge() > age) {
                int count = lisDEService.getCounPetLocCode(loc.getCode());
                int male = lisDEService.getCountPetByLocCodeMale(loc.getCode());
                int female = lisDEService.getCountPetByLocCodeFemale(loc.getCode());
                if (count > 0) {
                    petByLocationDTOS.add(new PetByLocationDTO(loc, female, male, count));
                }
            }
        }
        return new ResponseEntity<>(new ResponseDTO(
                200, petByLocationDTOS,
                null), HttpStatus.OK);
    }
    @GetMapping(path = "/PetsbyCityDE/{age}")
    public ResponseEntity<ResponseDTO> getPetsByCity(@PathVariable byte age) {
        List<PetByLocationDTO> petByLocationDTO = new ArrayList<>();
        for (Location loc : LocationService.getLocationsByCodeSize(8)) {
            if (lisDEService.getPets().getData().getAge() > age) {
                int count = lisDEService.getCounPetLocCode(loc.getCode());
                int male = lisDEService.getCountPetByLocCodeMale(loc.getCode());
                int female = lisDEService.getCountPetByLocCodeFemale(loc.getCode());
                if (count > 0) {
                    petByLocationDTO.add(new PetByLocationDTO(loc, female, male, count));
                }
            }
        }
        return new ResponseEntity<>(new ResponseDTO(
                200, petByLocationDTO,
                null), HttpStatus.OK);
    }


}
