package com.springsecurity.jpa.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String hello(){
        return "<h1>Hello ;)</h1>";
    }

    @GetMapping("/user")
    public String user(){
        return "<h1>Hello user</h1>";
    }

    @GetMapping("/amin")
    public String admin(){
        return "<h1>Hello admin</h1>";
    }


}
