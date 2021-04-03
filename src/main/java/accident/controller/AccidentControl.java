package accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import accident.model.Accident;
import accident.repository.AccidentMem;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccidentControl {

    @GetMapping("/create")
    public String create() {
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident) {
        AccidentMem.instOf().create(accident);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") int id, Model model) {
        Accident accident = AccidentMem.instOf().findAccidentById(id);
        model.addAttribute("accident", accident);
        return "accident/edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Accident accident, @RequestParam("id") int id) {
        accident.setId(id);
        AccidentMem.instOf().updateAccident(accident);
        return "redirect:/";
    }
}