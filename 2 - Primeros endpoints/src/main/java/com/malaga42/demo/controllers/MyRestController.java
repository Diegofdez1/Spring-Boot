package com.malaga42.demo.controllers;

import com.malaga42.demo.models.Member;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest")
public class MyRestController {

    @GetMapping({"/", ""})
    public String getEndpoint() {
        return "Nuestro primer endpoint resstt!!!!!";
    }

    @GetMapping("/json")
    public Member correctRestEndpoint() {
        return new Member("Test");
    }

    @PostMapping("/post")
    public Member postEndpoint(@RequestBody String name) {
        return new Member(name);
    }

}





