package io.github.k_gregory.registry.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DumbController {
    @GetMapping("/dumb")
    @ResponseBody
    public String dumb() {
        return "dumb";
    }
}
