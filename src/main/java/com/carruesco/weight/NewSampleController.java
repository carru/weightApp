package com.carruesco.weight;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NewSampleController {
	private static final Logger log = LoggerFactory.getLogger(NewSampleController.class);

    @GetMapping("/new")
    public String newSampleForm(Model model) {
    	log.debug("newSampleForm");
        return "newSample";
    }

}
