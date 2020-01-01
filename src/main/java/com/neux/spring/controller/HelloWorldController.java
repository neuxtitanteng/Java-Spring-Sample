package com.neux.spring.controller;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mvc")
public class HelloWorldController {

    @RequestMapping("/helloworld")
    public String helloworld(Model model) {
        return "helloworld";
    }

    @RequestMapping("/helloworld/{name}")
    public String helloworld(@PathVariable("name") String name , Model model) {

        model.addAttribute("name",name);

        return "helloworld";
    }


}
