package com.nongsa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AgricultureController {

    @GetMapping("/agriculture/calculator")
    public String calculator() {
        return "agriculture/calculator";
    }

    @GetMapping("/agriculture/tech")
    public String tech() {
        return "agriculture/tech";
    }

    @GetMapping("/agriculture/tech/callback")
    public String techCallback() {
        return "agriculture/tech-callback";
    }
}
