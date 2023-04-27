package co.edu.umanizales.tads.controller;

import co.edu.umanizales.tads.controller.dto.KidDTO;
import co.edu.umanizales.tads.controller.dto.KidsByLocationDTO;
import co.edu.umanizales.tads.controller.dto.ResponseDTO;
import co.edu.umanizales.tads.model.Kid;
import co.edu.umanizales.tads.model.Location;
import co.edu.umanizales.tads.service.LocationService;
import co.edu.umanizales.tads.service.ListSEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

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
    public ResponseEntity<ResponseDTO> invert(){
        listSEService.invert();
        return new ResponseEntity<>(new ResponseDTO(
                200,"SE ha invertido la lista",
                null), HttpStatus.OK);

    }

    @GetMapping(path = "/change_extremes")
    public ResponseEntity<ResponseDTO> changeExtremes() {
        listSEService.changeExtremes();
        return new ResponseEntity<>(new ResponseDTO(
                200,"SE han intercambiado los extremos",
                null), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> addKid(@RequestBody KidDTO kidDTO){
        Location location = LocationService.getLocationByCode(kidDTO.getCodeLocation());
        if (listSEService.verifyId(kidDTO)==0) {
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
        } else  {
            return new ResponseEntity<>(new ResponseDTO(400,"ya existe ese id",
                    null),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/kidsbylocations")
    public ResponseEntity<ResponseDTO> getKidsByLocation(){
        List<KidsByLocationDTO> kidsByLocationDTOList = new ArrayList<>();
        for(Location loc: LocationService.getLocations()){
            int count = listSEService.getKids().getCountKidsByLocationCode(loc.getCode());
            if(count>0){
                kidsByLocationDTOList.add(new KidsByLocationDTO(loc,count));
            }
        }
        return new ResponseEntity<>(new ResponseDTO(
                200,kidsByLocationDTOList,
                null), HttpStatus.OK);
    }

    @GetMapping(path = "/deletekig/{id}")
    public ResponseEntity<ResponseDTO>deleteKidbyage(byte age){
        listSEService.deleteKidbyage(age);
        return new ResponseEntity<>(new ResponseDTO(200,"niño eliminado",null),HttpStatus.OK);
    }
    @GetMapping(path = "/delete_kid/{id}")
    public ResponseEntity<ResponseDTO> deleteKid(@PathVariable String id) {
        listSEService.deleteKid(id);
        return new ResponseEntity<>(new ResponseDTO(
                200, "Niño eliminado", null), HttpStatus.OK);
    }
}
