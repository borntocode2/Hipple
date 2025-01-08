package com.example.hiddenplace.controller;

import com.example.hiddenplace.dto.RequestDto;
import com.example.hiddenplace.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody //? Search
@RequiredArgsConstructor
public class JoinController {

    private final JoinService joinService;

    @PostMapping("/join")
    public String JoinProcess(RequestDto requestDto) {
        joinService.JoinProcess(requestDto);
        return "ok";
    }
}
