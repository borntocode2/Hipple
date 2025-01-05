package com.example.hiddenplace.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody //웹이 아닌, 특정한 문자열을 리턴하는
public class MainController {

    @GetMapping("/") //루트
    public String mainP() {
        return "main Controller";
    }
}
