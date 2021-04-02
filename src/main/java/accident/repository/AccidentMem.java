package accident.repository;

import accident.model.Accident;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class AccidentMem {
    private static final HashMap<Integer, Accident> ACCIDENTS = new HashMap<>();

    public void addAccidents() {
        ACCIDENTS.put(1, new Accident(1, "name1", "text1", "address1"));
        ACCIDENTS.put(2, new Accident(2, "name2", "text2", "address2"));
        ACCIDENTS.put(3, new Accident(3, "name3", "text3", "address3"));
    }

    public HashMap<Integer, Accident> getAccidents() {
        addAccidents();
        return ACCIDENTS;
    }
}
