package com.snippify.snippify.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/snippets")
public class SnippetController {

    @GetMapping("/test")
    public String test() {
        return "Hello from Snippify!";
    }

}

