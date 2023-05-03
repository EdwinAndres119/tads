package co.edu.umanizales.tads.service;

import co.edu.umanizales.tads.controller.dto.KidDTO;
import co.edu.umanizales.tads.model.Kid;
import co.edu.umanizales.tads.model.ListSE;
import co.edu.umanizales.tads.model.Node;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class ListSEService {
    private ListSE kids;

    public ListSEService() {
        kids = new ListSE();
    }
    public void invert() {kids.invert();}
    public Node getKids() {return kids.getHead();}

    public void add (Kid kid){kids.add(kid);}
    public void deleteKidbyage(byte age){kids.deleteKidByage(age);}
    public void deleteKid(String id){kids.deleteKid(id);}
    public void changeExtremes () {kids.changeExtremes();}
    public void losePosition (String id, int lose){kids.losePosition(id,lose);}
    public void verifyid(KidDTO kidDTO){kids.verifyid(kidDTO);}

    public void orderByGender() {kids.orderBygender();}

    public void putKidsBeginning(){kids.putKidsBeginning();}
    public void WinPos(String id, int earn){kids.WinPos(id,earn);}
    public int getCountKidByLocationCode(String code){
        return  kids.getCountKidByLocationCode(code);
    }
    public String ReportByage(){return  kids.ReportByage();}
    public int getCountKidByLocCodeFemale(String code){return kids.getCountKidByLocCodeFemale(code);}

    public int getCountKidByLocCodeMale(String code){return kids.getCountKidByLocCodeMale(code);}
}


