package com.neux.spring.controller;

import org.json.JSONObject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restful")
public class RestfulHelloWorldController {

    @RequestMapping("/helloworld")
    public String helloworld() {
        return "helloworld";
    }

    @RequestMapping("/helloworld/{name}")
    public String helloworld(@PathVariable("name") String name) {
        return "helloworld " + name;
    }

    @RequestMapping("/helloworld/json/{name}")
    @ResponseBody
    public String helloworldToJson(@PathVariable("name") String name ) {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("name",name);

        return jsonObject.toString();
    }
}
