package com.udev.hotel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {
	@GetMapping("/hello")
    public String hello(@RequestParam(name = "resp" , defaultValue = "product") String resp) {
        return "hello " + resp;
    }
}
