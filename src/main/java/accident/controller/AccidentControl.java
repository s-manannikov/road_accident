package accident.controller;

import accident.model.AccidentType;
import accident.model.Rule;
import accident.repository.AccidentRepository;
import accident.repository.RuleRepository;
import accident.repository.TypeRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import accident.model.Accident;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class AccidentControl {
    private final AccidentRepository accidents;
    private final RuleRepository rules;
    private final TypeRepository types;

    public AccidentControl(AccidentRepository accidents, RuleRepository rules, TypeRepository types) {
        this.accidents = accidents;
        this.rules = rules;
        this.types = types;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        List<AccidentType> types = this.types.findAll();
        model.addAttribute("types", types);
        List<Rule> rules = this.rules.findAll();
        model.addAttribute("rules", rules);
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, @RequestParam("type.id") int id, HttpServletRequest req) {
        AccidentType type = new AccidentType();
        type.setId(id);
        accident.setType(type);
        String[] ids = req.getParameterValues("ruleIds");
        List<Integer> ruleIds = Arrays.stream(ids).map(Integer::parseInt).collect(Collectors.toList());
        List<Rule> rules = this.rules.findAllById(ruleIds);
        accident.setRules(rules);
        accidents.save(accident);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        List<AccidentType> types = this.types.findAll();
        model.addAttribute("types", types);
        Accident accident = this.accidents.findById(id).orElse(null);
        model.addAttribute("accident", accident);
        List<Rule> rules = this.rules.findAll();
        model.addAttribute("rules", rules);
        return "accident/edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Accident accident, @RequestParam("id") int id,
            @RequestParam("type.id") int typeId, HttpServletRequest req) {
        String[] ids = req.getParameterValues("ruleIds");
        List<Integer> ruleIds = Arrays.stream(ids).map(Integer::parseInt).collect(Collectors.toList());
        List<Rule> rules = this.rules.findAllById(ruleIds);
        accident.setId(id);
        accident.setRules(rules);
        accidents.save(accident);
        return "redirect:/";
    }
}