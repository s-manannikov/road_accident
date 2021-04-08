package accident.repository;

import accident.model.AccidentType;
import accident.model.Rule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import accident.model.Accident;

import java.util.*;

//@Repository
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void create(Accident accident) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbc);
        insert.setGeneratedKeyName("id");
        final Map<String, Object> data = new HashMap<>();
        data.put("name", accident.getName());
        data.put("text", accident.getText());
        data.put("address", accident.getAddress());
        data.put("type_id", accident.getType().getId());
        final List<String> columns = new ArrayList<>();
        columns.add("name");
        columns.add("text");
        columns.add("address");
        columns.add("type_id");
        insert.setTableName("accident");
        insert.setColumnNames(columns);
        final Number id = insert.executeAndReturnKey(data);
        for (Rule rule : accident.getRules()) {
            jdbc.update("insert into accident_rule (accident_id, rule_id) values (?, ?)",
                    id,
                    rule.getId()
            );
        }
    }

    public List<Accident> getAccidents() {
        return jdbc.query("" +
                        "select accident.*, type.name as type_name, " +
                        "accident_rule.rule_id as rule_id, rule.name as rule_name from accident " +
                        "join type on accident.type_id = type.id " +
                        "join accident_rule on accident_id = accident.id " +
                        "join rule on rule_id = rule.id",
                rs -> {
            final Map<Integer, Accident> accidents = new HashMap<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                if (accidents.containsKey(id)) {
                    final Rule rule = new Rule();
                    rule.setId(rs.getInt("rule_id"));
                    rule.setName(rs.getString("rule_name"));
                    accidents.get(id).getRules().add(rule);
                } else {
                    final Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));
                    final AccidentType type = new AccidentType();
                    type.setId(rs.getInt("type_id"));
                    type.setName(rs.getString("type_name"));
                    accident.setType(type);
                    List<Rule> set = new ArrayList<>();
                    final Rule rule = new Rule();
                    rule.setId(rs.getInt("rule_id"));
                    rule.setName(rs.getString("rule_name"));
                    set.add(rule);
                    accident.setRules(set);
                    accidents.put(id, accident);
                }
            }
            return new ArrayList<>(accidents.values());
        });
    }

    public Accident findAccidentById(int id) {
        return jdbc.queryForObject(
                "select accident.*, type.name as type_name from accident " +
                        "join type on accident.type_id = type.id where accident.id = ?",
                (rs, row) -> {
                    final Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));
                    final AccidentType type = new AccidentType();
                    type.setId(rs.getInt("type_id"));
                    type.setName(rs.getString("type_name"));
                    accident.setType(type);
                    return accident;
                }, id);
    }

    public void updateAccident(Accident accident) {
        jdbc.update("update accident set name = ?, text = ?, address = ?, type_id = ? where id = ?",
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getType().getId(),
                accident.getId());
        jdbc.update("delete from accident_rule where accident_id = ?",
                accident.getId());
        for (Rule rule : accident.getRules()) {
            jdbc.update(
                    "insert into accident_rule (accident_id, rule_id) values (?, ?)",
                    accident.getId(),
                    rule.getId()
            );
        }
    }

    public List<AccidentType> getAccidentTypes() {
        return jdbc.query("select id, name from type",
                (rs, row) -> {
                    final AccidentType type = new AccidentType();
                    type.setId(rs.getInt("id"));
                    type.setName(rs.getString("name"));
                    return type;
                });
    }

    public AccidentType findTypeById(int id) {
        return jdbc.queryForObject("select * from type where id = ?",
                (rs, row) -> {
                    final AccidentType type = new AccidentType();
                    type.setId(rs.getInt("id"));
                    type.setName(rs.getString("name"));
                    return type;
                }, id);
    }

    public List<Rule> getRules() {
        return jdbc.query("select * from rule",
                (rs, row) -> {
                    final Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                });
    }

    public Set<Rule> findRulesById(String[] ids) {
        Set<Rule> rules = new HashSet<>();
        for (String id : ids) {
            Rule resultRule = jdbc.queryForObject("select * from rule where id = ?",
                    (rs, row) -> {
                        final Rule rule = new Rule();
                        rule.setId(rs.getInt("id"));
                        rule.setName(rs.getString("name"));
                        return rule;
                    }, Integer.parseInt(id));
            rules.add(resultRule);
        }
        return rules;
    }
}
 