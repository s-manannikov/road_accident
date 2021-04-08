package accident.controller;

import accident.model.AccidentType;
import accident.model.Rule;
import accident.repository.AccidentJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import accident.model.Accident;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class AccidentControl {
    private final AccidentJdbcTemplate accidents;

    public AccidentControl(AccidentJdbcTemplate accidents) {
        this.accidents = accidents;
    }

    @GetMapping("/create")
    public String create(Model model) {
        List<AccidentType> types = new ArrayList<>(accidents.getAccidentTypes());
        model.addAttribute("types", types);
        List<Rule> rules = new ArrayList<>(accidents.getRules());
        model.addAttribute("rules", rules);
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, @RequestParam("type.id") int id, HttpServletRequest req) {
        AccidentType type = accidents.findTypeById(id);
        String[] ids = req.getParameterValues("ruleIds");
        Set<Rule> rules = accidents.findRulesById(ids);
        accident.setRules(rules);
        accident.setType(type);
        accidents.create(accident);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") int id, Model model) {
        List<AccidentType> types = new ArrayList<>(accidents.getAccidentTypes());
        model.addAttribute("types", types);
        Accident accident = accidents.findAccidentById(id);
        model.addAttribute("accident", accident);
        List<Rule> rules = new ArrayList<>(accidents.getRules());
        model.addAttribute("rules", rules);
        return "accident/edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Accident accident, @RequestParam("id") int id,
            @RequestParam("type.id") int typeId, HttpServletRequest req) {
        AccidentType type = accidents.findTypeById(typeId);
        String[] ids = req.getParameterValues("ruleIds");
        Set<Rule> rules = accidents.findRulesById(ids);
        accident.setRules(rules);
        accident.setType(type);
        accident.setId(id);
        accidents.updateAccident(accident);
        return "redirect:/";
    }
}