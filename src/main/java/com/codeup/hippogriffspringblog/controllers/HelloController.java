package com.codeup.hippogriffspringblog.controllers;

import org.apache.logging.log4j.message.Message;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
public class HelloController {
    private int counter;

    @PostMapping("/testpost")
    @ResponseBody
    public String testPost() {
        return "testPost called";
    }

    @GetMapping("/counter/{counterInit}/stuff")
    @ResponseBody
    public String initCounter(@PathVariable int counterInit) {
        counter = counterInit;
        return "counter reset to " + counter;
    }

    @GetMapping("/increment")
    @ResponseBody
    public String incrementCounter() {
        counter++;
        return "counter is now " + counter;
    }



//    @RequestMapping(method = RequestMethod.GET, name = "/hello")
    @GetMapping(path = "/hello/{personName}")
    public String hello(@PathVariable String personName,
                        Model model) {
        model.addAttribute("name", personName);
        return "hello";
    }



    @GetMapping(path = "/hello-msg/{personName}", produces = "application/json")
    @ResponseBody
    public String helloMessage(@PathVariable String personName) {
        return String.format("Hello from JSON! %s %s", personName, LocalDateTime.now());
    }

//    @RequestMapping(path = "/increment/{number}", method = RequestMethod.GET)
//    @ResponseBody
//    public String addOne(@PathVariable int number) {
//        return number + " plus one is " + (number + 1) + "!";
//    }
}
