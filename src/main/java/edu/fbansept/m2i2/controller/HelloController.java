package edu.fbansept.m2i2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String bonjour() {
        return "Bonjour";
    }

}
