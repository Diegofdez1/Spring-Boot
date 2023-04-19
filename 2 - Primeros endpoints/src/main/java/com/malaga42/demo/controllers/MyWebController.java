package com.malaga42.demo.controllers;

import com.malaga42.demo.models.Member;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/web")
public class MyWebController {

    @GetMapping({"", "/"})
    public ModelAndView firstEndpoint() {
        return new ModelAndView("first_template");
    }

    @GetMapping("/json")
    public Member incorrectRestEndpoint() {
        return new Member("Test");
    }

}