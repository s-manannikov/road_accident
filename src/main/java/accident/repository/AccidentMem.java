package accident.repository;

import accident.model.Accident;
import accident.model.AccidentType;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class AccidentMem {
    private final static HashMap<Integer, Accident> ACCIDENTS = new HashMap<>();
    private final static HashMap<Integer, AccidentType> ACCIDENT_TYPES = new HashMap<>();
    private static int ID = 1;

    private AccidentMem() {
        ACCIDENT_TYPES.put(1, AccidentType.of(1, "Two cars"));
        ACCIDENT_TYPES.put(2, AccidentType.of(2, "Car and human"));
        ACCIDENT_TYPES.put(3, AccidentType.of(3, "Car and bicycle"));
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

    public HashMap<Integer, AccidentType> getAccidentTypes() {
        return ACCIDENT_TYPES;
    }

    public AccidentType findTypeById(int id) {
        return ACCIDENT_TYPES.get(id);
    }
}
