package com.example.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @PROJECT_NAME: supermarket
 * @DESCRIPTION:
 * @USER: 韩冰
 * @DATE: 2022/9/14 0014 17:07
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String Hello(){
        return "hello";
    }
}
