package com.rentingbook.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("")
    public String showIndex() {
        return "https://github.com/khanh1998/RentingBookSystemAPI";
    }
}
