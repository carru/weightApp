package com.carruesco.weight;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GraphController {

    @RequestMapping("/")
    public String greeting(Model model) {
    	Weight weight = new Weight(new Date(), 84.5, 1.815);
        model.addAttribute("data", weight);
        return "weight";
    }

}