package com.gm.EcommerceBackend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DemoController {

    @GetMapping("/public/hello")
    public String public_endpoint(){
        return "Hello!";
    }

    @GetMapping("/user/hello")
    public String user_endpoint(){
        return "Hello!";
    }

    @GetMapping("/admin/hello")
    public String admin_endpoint(){
        return "Hello!";
    }
}
