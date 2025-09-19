package com.poppang.api.poppanggoogle.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoogleLoginController {

    @GetMapping("/hello")
    public String hello() {
        return "PopPang Google Backend Running 🚀";
    }
}

