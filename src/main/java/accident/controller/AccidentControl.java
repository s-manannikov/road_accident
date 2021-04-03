package accident.controller;

import accident.model.AccidentType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import accident.model.Accident;
import accident.repository.AccidentMem;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AccidentControl {

    @GetMapping("/create")
    public String create(Model model) {
        List<AccidentType> types = new ArrayList<>(AccidentMem.instOf().getAccidentTypes().values());
        model.addAttribute("types", types);
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, @RequestParam("type.id") int id) {
        AccidentType type = AccidentMem.instOf().findTypeById(id);
        accident.setType(type);
        AccidentMem.instOf().create(accident);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") int id, Model model) {
        List<AccidentType> types = new ArrayList<>(AccidentMem.instOf().getAccidentTypes().values());
        model.addAttribute("types", types);
        Accident accident = AccidentMem.instOf().findAccidentById(id);
        model.addAttribute("accident", accident);
        return "accident/edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Accident accident, @RequestParam("id") int id, @RequestParam("type.id") int typeId) {
        AccidentType type = AccidentMem.instOf().findTypeById(typeId);
        accident.setType(type);
        accident.setId(id);
        AccidentMem.instOf().updateAccident(accident);
        return "redirect:/";
    }
}