package com.vn.devmaster.services.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //định nghĩa controller
public class DemoController {
    @GetMapping("")
    public String demo(){
        return "hello Springboot";
    }
}
