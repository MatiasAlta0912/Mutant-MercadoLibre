package com.example.mutant.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    @GetMapping("/")
    public String root() {
        return "Mutant Detector API – visitar /swagger-ui/index.html para ver la documentación.";
    }
}
