package accident.controller;

import accident.model.Accident;
import accident.repository.AccidentJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexControl {
    private final AccidentJdbcTemplate accidents;

    public IndexControl(AccidentJdbcTemplate accidents) {
        this.accidents = accidents;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Accident> accidents = new ArrayList<>(this.accidents.getAccidents());
        model.addAttribute("accidents", accidents);
        return "index";
    }
}