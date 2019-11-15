package com.spring5.springrecepie.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecepieController {
    @RequestMapping({"/","/index","/index.html"})
    public String showIndex(){
        return "index";
    }
}
