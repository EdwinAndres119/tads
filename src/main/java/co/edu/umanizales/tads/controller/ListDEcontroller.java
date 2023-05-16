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
@RequestMapping(path = "lis_De")
public class ListDEcontroller {
    @Autowired
    private ListDEservice lisDEService;
    @Autowired
    private LocationService LocationService;

    @GetMapping(path = "/get_List")
    public ResponseEntity<ResponseDTO> getPets() {
        return new ResponseEntity<>(new ResponseDTO(200, lisDEService.getPets(), null), HttpStatus.OK);
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
    public ResponseEntity<ResponseDTO> changeExtremes() throws ListDeException{
        try {
            lisDEService.changeExtremes();
            return new ResponseEntity<>(new ResponseDTO(
                    200, "SE han intercambiado los extremos",
                    null), HttpStatus.OK);
        }catch (ListDeException e){
            throw new RequestException(e.getCode(),e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> addPet(@Valid @RequestBody PetDTO petDTO ) throws ListDeException {
        try {
            Location location = LocationService.getLocationByCode(petDTO.getCodeLocation());
            if (location == null) {
                return new ResponseEntity<>(new ResponseDTO(
                        404, "La ubicación no existe",
                        null), HttpStatus.BAD_REQUEST);
            }
            ListDEservice.addPet(
                    new Pet(petDTO.getAge(),petDTO.getName(),petDTO.getGender(),
                        petDTO.getOwnernumb(),petDTO.getBreed(),location));
            return new ResponseEntity<>(new ResponseDTO(
                    200, "Se ha adicionado el petacón",
                    null), HttpStatus.OK);

        }catch (ListDeException e){
            throw new RequestException(e.getCode(),e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/delete_pet/{id}")
    public ResponseEntity<ResponseDTO> deletePet(@PathVariable String id) throws ListDeException {
        try {
            lisDEService.deletePet(id);
            return new ResponseEntity<>(new ResponseDTO(
                    200, "Mascota eliminada", null), HttpStatus.OK);
        } catch (ListDeException e) {
            throw new RequestException(e.getCode(), e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping(path = "/lose_positions")
    public ResponseEntity<ResponseDTO> losePositions(@PathVariable byte age) throws ListDeException {
        try {
            lisDEService.deletePetByage(age);
            return new ResponseEntity<>(new ResponseDTO(
                    200, "posiciones reordenadas", null), HttpStatus.OK);
        }catch (ListDeException e){
            throw new RequestException(e.getCode(),e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/orderByGender")
    public ResponseEntity<ResponseDTO> orderByGender() throws ListDeException{
        try {


            lisDEService.orderByGender();
            return new ResponseEntity<>(new ResponseDTO(
                    200, "los mascotas fueron ordenados", null), HttpStatus.OK);
        }catch (ListDeException e){
            throw new RequestException(e.getCode(),e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/put_pets_beginning")
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
    public ResponseEntity<ResponseDTO> WinPos(@RequestBody Map<String, Object> requestBody)throws ListDeException {
        try {

            String id = (String) requestBody.get("id");
            Integer earn = (Integer) requestBody.get("earn");
            lisDEService.WinPos(id, earn);
            return new ResponseEntity<>(new ResponseDTO(
                    200, "posiciones re ordenadas", null), HttpStatus.OK);
        }catch (ListDeException e){
            throw new ListDeException(e.getCode(),e.getMessage());
        }
    }

    @PostMapping(path = "/add_pet_to_beginning")
    public ResponseEntity<ResponseDTO> addPetToBeginning(@Valid @RequestBody PetDTO petDTO) throws ListDeException{
        try {
            Location location = LocationService.getLocationByCode(petDTO.getCodeLocation());
            if (location == null)
                return new ResponseEntity<>(new ResponseDTO(404, "la ubicacion requerida no existe",null),HttpStatus.OK);

        lisDEService.addPetToBeginning(new Pet(petDTO.getAge(),petDTO.getName(),petDTO.getGender(),petDTO.getOwnernumb(),petDTO.getBreed(),location) );
        return new ResponseEntity<>(new ResponseDTO(200, "se adiciono el perro",null),HttpStatus.OK);
        }catch (ListDeException e){
            throw new RequestException(e.getCode(),e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/add_pet_in_pos/{pos}")
    public ResponseEntity<ResponseDTO> addPetInPos(@Valid @RequestBody PetDTO petDTO,@Min (0)@PathVariable int pos) {
        if (lisDEService.verifyPhone(petDTO) == 0) {
            Location location = LocationService.getLocationByCode(petDTO.getCodeLocation());
            if (location == null) {
                return new ResponseEntity<>(new ResponseDTO(
                        404, "La ubicación no existe",
                        null), HttpStatus.BAD_REQUEST);
            }
            lisDEService.addPetInPos(
                    new Pet(petDTO.getAge(),petDTO.getName(),petDTO.getGender(),
                            petDTO.getOwnernumb(),petDTO.getBreed(),location),pos);
            return new ResponseEntity<>(new ResponseDTO(
                    200, "Se ha adicionado el petacón",
                    null), HttpStatus.OK);
        } else {
            List<ErrorDTO> errorDTOS = new ArrayList<>();
            ErrorDTO errorDTO = new ErrorDTO(400, "digite una mascota con un id distinto");
            errorDTOS.add(errorDTO);
            return new ResponseEntity<>(new ResponseDTO(400, "Ya existe una mascota con ese id", errorDTOS), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/get_average_age")
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
    public ResponseEntity<ResponseDTO> sendPetsToEndByChar(@RequestBody Map<String, Object> requestData) {
        try {
            String userString = (String) requestData.get("user");
            char user = userString.toLowerCase().charAt(0);
            lisDEService.sendPetsToEndByChar(user);
            return new ResponseEntity<>(new ResponseDTO(
                    200, "mascotas ordenadas", null), HttpStatus.OK);
        }catch (ListDeException e){
            throw new RequestException(e.getCode(),e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/generate_report_by_age")
    public ResponseEntity<ResponseDTO> ReportByage() throws ListDeException{
        try {
            return new ResponseEntity<>(new ResponseDTO(200, lisDEService.ReportByage(), null), HttpStatus.OK);
        }catch (ListDeException e){
            throw new RequestException(e.getCode(),e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/delete_kid/{id}")
    public ResponseEntity<ResponseDTO> RemovePetInPosition(@PathVariable String id, @RequestParam int pos1) throws ListDeException {
        try {
            lisDEService.RemovePetInPosition(id, pos1);
        } catch (ListSEException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(new ResponseDTO(200, "Mascota eliminada", null), HttpStatus.OK);
    }
    @GetMapping(path = "/kidsbyDepartment/{age}")
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

    @GetMapping(path = "/pets_by_locations/{age}")
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
    @GetMapping(path = "/kidsbyCity/{age}")
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
