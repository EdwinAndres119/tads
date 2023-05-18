package co.edu.umanizales.tads.controller;

import co.edu.umanizales.tads.controller.dto.KidDTO;
import co.edu.umanizales.tads.controller.dto.KidsByLocationDTO;
import co.edu.umanizales.tads.controller.dto.ResponseDTO;
import co.edu.umanizales.tads.exception.ListDeException;
import co.edu.umanizales.tads.exception.ListSEException;
import co.edu.umanizales.tads.exception.RequestException;
import co.edu.umanizales.tads.model.Kid;
import co.edu.umanizales.tads.model.Location;
import co.edu.umanizales.tads.service.LocationService;
import co.edu.umanizales.tads.service.ListSEService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/listse")
public class ListSEController {
    @Autowired
    private ListSEService listSEService;
    @Autowired
    private LocationService LocationService;

    @GetMapping(path = "/get_List")
    public ResponseEntity<ResponseDTO> getKids(){
        return new ResponseEntity<>(new ResponseDTO(200, listSEService.getKids(),null),HttpStatus.OK);
    }
    @GetMapping("/invert")
    public ResponseEntity<ResponseDTO> invert() throws ListDeException {
        try {
            listSEService.invert();
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
            listSEService.changeExtremes();
            return new ResponseEntity<>(new ResponseDTO(
                    200, "SE han intercambiado los extremos",
                    null), HttpStatus.OK);
        }catch (ListDeException e){
            throw new RequestException(e.getCode(),e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> addKid(@RequestBody @Valid @NotNull KidDTO kidDTO) throws ListSEException {
        try {
            Location location = LocationService.getLocationByCode(kidDTO.getCodeLocation());
            if (location == null) {
                return new ResponseEntity<>(new ResponseDTO(
                        404, "La ubicación no existe",
                        null), HttpStatus.OK);
            }
            listSEService.add(
                    new Kid(kidDTO.getIdentification(),
                            kidDTO.getName(), kidDTO.getAge(),
                            kidDTO.getGender(), location));
            return new ResponseEntity<>(new ResponseDTO(
                    200, "Se ha adicionado el petacón",
                    null), HttpStatus.OK);
        } catch (ListSEException e) {
            throw new RequestException(e.getCode(), e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/delete_kid_by_age/{age}")
    public ResponseEntity<ResponseDTO>deleteKidbyage(byte age){
        listSEService.deleteKidbyage(age);
        return new ResponseEntity<>(new ResponseDTO(200,"niño eliminado",null),HttpStatus.OK);
    }
    @GetMapping(path = "/delete_kid_by_id/{id}")
    public ResponseEntity<ResponseDTO> deleteKid(@PathVariable String id) {
            listSEService.deleteKid(id);
            return new ResponseEntity<>(new ResponseDTO(
                    200, "Niño eliminado", null), HttpStatus.OK);

    }
    @GetMapping(path = "/kidsbyCity/{age}")
    public ResponseEntity<ResponseDTO> getKidsByCity(@PathVariable byte age){
        List<KidsByLocationDTO> kidsByLocationDTOList = new ArrayList<>();
        for(Location loc: LocationService.getLocationsByCodeSize(8)){
            if (listSEService.getKids().getData().getAge() > age) {
                int count = listSEService.getCountKidByLocationCode(loc.getCode());
                int male = listSEService.getCountKidByLocCodeMale(loc.getCode());
                int female = listSEService.getCountKidByLocCodeFemale(loc.getCode());
                if (count > 0) {
                    kidsByLocationDTOList.add(new KidsByLocationDTO(loc, female, male, count));
                }
            }
        }
        return new ResponseEntity<>(new ResponseDTO(
                200,kidsByLocationDTOList,
                null), HttpStatus.OK);
    }
    @GetMapping(path = "/orderByGender")
    public ResponseEntity<ResponseDTO> orderByGender() throws ListSEException {
        listSEService.orderByGender();
        return new ResponseEntity<>(new ResponseDTO(
                200, "los niños fueron ordenados" , null), HttpStatus.OK);
    }

    @GetMapping(path = "/put_Kids_Beginning")
    public ResponseEntity<ResponseDTO> putKidsBeginning() throws ListSEException {
        listSEService.putKidsBeginning();
        return new ResponseEntity<>(new ResponseDTO(
                200, "niños agregados al inicio y niñas agregadas al final", null), HttpStatus.OK);
    }
    @PostMapping(path = "/win_positions")
    public ResponseEntity<ResponseDTO> WinPos(@RequestBody Map<String, Object> requestBody)throws ListSEException {
        try {


            String id = (String) requestBody.get("id");
            Integer win = (Integer) requestBody.get("win");
            listSEService.WinPos(id, win);
            return new ResponseEntity<>(new ResponseDTO(
                    200, "posiciones re ordenadas", null), HttpStatus.OK);
        }catch (ListSEException e){
            throw new ListSEException(e.getCode(),e.getMessage());
        }
}
    @GetMapping(path = "/generate_report_by_age")
    public ResponseEntity<ResponseDTO>  ReportByage(){
        return new ResponseEntity<>(new ResponseDTO(200, listSEService.ReportByage(), null), HttpStatus.OK);
    }
    @PostMapping(path = "/lose_positions")
    public ResponseEntity<ResponseDTO> losePosition(@RequestBody Map<String, Object> requestBody) throws ListSEException {
        String id = (String) requestBody.get("id");
        Integer lose = (Integer) requestBody.get("lose");
        listSEService.losePosition(id,lose);
        return new ResponseEntity<>(new ResponseDTO(
                200, "posiciones re ordenadas", null), HttpStatus.OK);
    }

    @GetMapping(path = "/kidsbyDepartment/{age}")
    public ResponseEntity<ResponseDTO> getKidsByDepartment(@PathVariable byte age){
        List<KidsByLocationDTO> kidsByLocationDTOList = new ArrayList<>();
        for(Location loc: LocationService.getLocationsByCodeSize(5)){
            if (listSEService.getKids().getData().getAge() > age) {
                int count = listSEService.getCountKidByLocationCode(loc.getCode());
                int male = listSEService.getCountKidByLocCodeMale(loc.getCode());
                int female = listSEService.getCountKidByLocCodeFemale(loc.getCode());
                if (count > 0) {
                    kidsByLocationDTOList.add(new KidsByLocationDTO(loc, female, male, count));
                }
            }
        }
        return new ResponseEntity<>(new ResponseDTO(
                200,kidsByLocationDTOList,
                null), HttpStatus.OK);
    }
    @GetMapping(path = "/kids_by_locations/{age}")
    public ResponseEntity<ResponseDTO> getKidsByLocation(@PathVariable byte age){
        List<KidsByLocationDTO> kidsByLocationDTO = new ArrayList<>();
        for(Location loc: LocationService.getLocations()){
            if (listSEService.getKids().getData().getAge() > age) {
                int count = listSEService.getCountKidByLocationCode(loc.getCode());
                int male = listSEService.getCountKidByLocCodeMale(loc.getCode());
                int female = listSEService.getCountKidByLocCodeFemale(loc.getCode());
                if (count > 0) {
                    kidsByLocationDTO.add(new KidsByLocationDTO(loc, female, male, count));
                }
            }
        }
        return new ResponseEntity<>(new ResponseDTO(
                200,kidsByLocationDTO,
                null), HttpStatus.OK);
    }
    @PostMapping(path ="/send_kidsend_char")
    public ResponseEntity<ResponseDTO> sendKidsToEndByChar(@RequestBody Map<String, Object> requestData)throws ListSEException{
        try {
            String userString = (String) requestData.get("user");
            char user = userString.toLowerCase().charAt(0);
            listSEService.sendKidsToByChar(user);
            return new ResponseEntity<>(new ResponseDTO(
                    200, "ordenados", null), HttpStatus.OK);
        }catch(ListSEException e){
            throw new RequestException(e.getCode(),e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(path = "/get_averageAge")
    public ResponseEntity<ResponseDTO> get_average_age(){
        try {
            return new ResponseEntity<>(new ResponseDTO(200,"el promedio de los niños es:"+ listSEService.getAvereageAge(),null),HttpStatus.OK);
        }catch (ListSEException e){
            throw new RequestException(e.getCode(),e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}