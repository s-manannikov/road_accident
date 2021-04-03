package accident.controller;

import accident.model.Accident;
import accident.repository.AccidentMem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexControl {
    @GetMapping("/")
    public String index(Model model) {
        List<Accident> accidents = new ArrayList<>(AccidentMem.instOf().getAccidents().values());
        model.addAttribute("accidents", accidents);
        return "index";
    }
}