package co.edu.umanizales.tads.service;

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
    }
}