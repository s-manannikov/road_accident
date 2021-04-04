package accident.repository;

import accident.model.Accident;
import accident.model.AccidentType;
import accident.model.Rule;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class AccidentMem {
    private final static HashMap<Integer, Accident> ACCIDENTS = new HashMap<>();
    private final static HashMap<Integer, AccidentType> ACCIDENT_TYPES = new HashMap<>();
    private final static HashMap<Integer, Rule> RULES = new HashMap<>();
    private static int ID = 1;

    private AccidentMem() {
        ACCIDENT_TYPES.put(1, AccidentType.of(1, "Two cars"));
        ACCIDENT_TYPES.put(2, AccidentType.of(2, "Car and human"));
        ACCIDENT_TYPES.put(3, AccidentType.of(3, "Car and bicycle"));
        RULES.put(1, Rule.of(1, "Rule 1"));
        RULES.put(2, Rule.of(2, "Rule 2"));
        RULES.put(3, Rule.of(3, "Rule 3"));
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

    public HashMap<Integer, Rule> getRules() {
        return RULES;
    }

    public Set<Rule> findRulesById(String[] ids) {
        return Arrays.stream(ids).map(i -> RULES.get(Integer.parseInt(i))).collect(Collectors.toSet());
    }
}
