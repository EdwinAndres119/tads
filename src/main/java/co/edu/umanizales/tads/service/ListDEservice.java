package co.edu.umanizales.tads.service;

import co.edu.umanizales.tads.controller.dto.PetDTO;
import co.edu.umanizales.tads.model.ListDE;
import co.edu.umanizales.tads.model.NodeDE;
import co.edu.umanizales.tads.model.Pet;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class ListDEservice {
    private ListDE pets;

    public ListDEservice() {
        pets = new ListDE();
    }

    public NodeDE getPets(){return pets.getHead();}
    public void addPet(Pet pet){pets.addPet(pet);}
    public void addPetToBeginning(Pet pet){pets.addPetToBeginning(pet);}
    public void deletePet(String name){pets.deletePet(name);}
    public void addPetInPos(Pet pet, int pos){pets.addPetInPos(pet, pos);}

    public void orderByGender(){pets.orderByGender();}
    public void losePositions(String phone,int lose){pets.losePositions(phone,lose);}
    public void changeExtremes(){pets.changeExtremes();}
    public void invertList(){pets.invert();}
    public void putPetsMaleToBeginning(){pets.putPetBeginning();}

    public double getHalfAgeDog(){return pets.getHalfAgeDog();}
    public void WinPos(String phone, int earn){pets.winPos(phone,earn);}
    public String ReportByage(){return pets.ReportByage();}
    public int verifyPhone(PetDTO petDTO){return pets.verifyPhone(petDTO);}
    public int getCounPetLocCode(String code){return pets.getCounPetLocCode(code);}
    public int getCountPetByLocCodeMale(String code){return pets.getCountPetByLocCodeMale(code);}
    public int getCountPetByLocCodeFemale(String code){return pets.getCountPetByLocCodeFemale(code);}

    public void RemovePetInPosition(String Phone){pets.deletePet(Phone);}
    public void sendPetsToEndByChar(char user) {pets.sendPetsToEndByChar(user);}
}
