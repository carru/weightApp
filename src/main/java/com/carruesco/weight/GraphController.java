package com.carruesco.weight;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GraphController {

    @RequestMapping("/")
    public String greeting(Model model) {
        return "weightGraph";
    }

}