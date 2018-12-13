package org.chris.study.template.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TemplateController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
