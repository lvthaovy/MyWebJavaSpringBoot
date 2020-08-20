package org.demo.myshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class WebController {


    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "index";
    }

//    @GetMapping("/user")
//    public String userIndex() {
//        return "user/index";
//    }
//
//    @GetMapping("/login")
//    public String login() {
//        return "login";
//    }
//
//    @GetMapping("/access-denied")
//    public String accessDenied() {
//        return "/error/access-denied";
//    }
}
