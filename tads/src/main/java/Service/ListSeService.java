package Service;

import lombok.Data;
import model.listaSe;
import org.springframework.stereotype.Service;

@Data
@Service
public class ListSeService {
    private listaSe Kids;
    public ListSeService(kid kid){
        kid.add(kid);
        
    }

    public void deletKid(String id) {
        Kids.deleteKid(id);
    }
}


