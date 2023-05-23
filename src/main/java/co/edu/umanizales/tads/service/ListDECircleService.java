package co.edu.umanizales.tads.service;

import co.edu.umanizales.tads.model.LIstDECircle;
import co.edu.umanizales.tads.model.NodeDE;
import co.edu.umanizales.tads.model.Pet;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Data
@Service
public class ListDECircleService {
    private LIstDECircle pets;

    public ListDECircleService() {
        pets = new LIstDECircle();
    }
    public NodeDE getPets (){
        return pets.getHead();
    }
    public void addPetToEnd(Pet pet){
        pets.addPetToEnd(pet);
    }
    public void addPetToBeginning(Pet pet){
        pets.addPetToBeginning(pet);
    }
    public void addInPos(Pet pet,int pos){
        pets.addInPos(pet,pos);
    }
    public void cleanPet(char direction){
        pets.cleanPet(direction);
    }
    public ArrayList<Pet> showList(){
        return pets.showList();
    }
}
