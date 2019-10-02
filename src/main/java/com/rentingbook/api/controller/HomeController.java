package com.rentingbook.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public ResponseEntity<String> showIndex() {
        return ResponseEntity.ok("https://github.com/khanh1998/RentingBookSystemAPI");
    }
}
