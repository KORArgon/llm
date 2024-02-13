package com.argon.llm.controller;

import com.argon.llm.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    // weatherService
    private final OpenAiService openAiService;

    @GetMapping("/")
    public String main(){

        openAiService.llmService();

        return "main";
    }

}
