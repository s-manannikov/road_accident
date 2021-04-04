package accident.controller;

import accident.model.AccidentType;
import accident.model.Rule;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import accident.model.Accident;
import accident.repository.AccidentMem;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class AccidentControl {
    private static final AccidentMem REPOSITORY = AccidentMem.instOf();

    @GetMapping("/create")
    public String create(Model model) {
        List<AccidentType> types = new ArrayList<>(REPOSITORY.getAccidentTypes().values());
        model.addAttribute("types", types);
        List<Rule> rules = new ArrayList<>(REPOSITORY.getRules().values());
        model.addAttribute("rules", rules);
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, @RequestParam("type.id") int id, HttpServletRequest req) {
        AccidentType type = REPOSITORY.findTypeById(id);
        String[] ids = req.getParameterValues("ruleIds");
        Set<Rule> rules = REPOSITORY.findRulesById(ids);
        accident.setRules(rules);
        accident.setType(type);
        REPOSITORY.create(accident);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") int id, Model model) {
        List<AccidentType> types = new ArrayList<>(REPOSITORY.getAccidentTypes().values());
        model.addAttribute("types", types);
        Accident accident = REPOSITORY.findAccidentById(id);
        model.addAttribute("accident", accident);
        List<Rule> rules = new ArrayList<>(REPOSITORY.getRules().values());
        model.addAttribute("rules", rules);
        return "accident/edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Accident accident, @RequestParam("id") int id,
            @RequestParam("type.id") int typeId, HttpServletRequest req) {
        AccidentType type = REPOSITORY.findTypeById(typeId);
        String[] ids = req.getParameterValues("ruleIds");
        Set<Rule> rules = REPOSITORY.findRulesById(ids);
        accident.setRules(rules);
        accident.setType(type);
        accident.setId(id);
        REPOSITORY.updateAccident(accident);
        return "redirect:/";
    }
}