package accident.repository;

import accident.model.Accident;
import org.springframework.stereotype.Repository;
import java.util.HashMap;

@Repository
public class AccidentMem {
    private final static HashMap<Integer, Accident> ACCIDENTS = new HashMap<>();
    private static int ID = 4;

    private AccidentMem() {
        ACCIDENTS.put(1, new Accident("name1", "text1", "address1"));
        ACCIDENTS.put(2, new Accident("name2", "text2", "address2"));
        ACCIDENTS.put(3, new Accident("name3", "text3", "address3"));
    }

    private static final class Holder {
        public static final AccidentMem INST = new AccidentMem();
    }

    public static AccidentMem instOf() {
        return Holder.INST;
    }

    public HashMap<Integer, Accident> getAccidents() {
        return ACCIDENTS;
    }

    public void create(Accident accident) {
        accident.setId(ID);
        ACCIDENTS.put(ID++, accident);
    }

    public Accident findAccidentById(int id) {
        return ACCIDENTS.get(id);
    }

    public void updateAccident(Accident accident) {
        ACCIDENTS.put(accident.getId(), accident);
    }
}
