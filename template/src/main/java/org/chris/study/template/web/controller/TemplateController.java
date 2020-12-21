package org.chris.study.template.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TemplateController {

    @RequestMapping("/")
    public String index(@RequestParam String code) {
        System.out.println(code);
        return "Greetings from Spring Boot!";
    }
}
