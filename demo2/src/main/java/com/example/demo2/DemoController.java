package com.example.demo2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
@RestController //định nghĩa controller
public class DemoController {
        @GetMapping("")
        @RequestMapping(name="/demo2",method = RequestMethod.GET)
        public String demo2(){
            return "hello Springboot";
        }
    }