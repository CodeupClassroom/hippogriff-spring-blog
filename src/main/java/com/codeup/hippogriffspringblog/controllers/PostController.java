package com.codeup.hippogriffspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/posts")
public class PostController {

    @GetMapping("/")
    @ResponseBody
    public String getAllPosts() {
        return "posts index page";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public String getPostDetail(@PathVariable long id) {
        return "view an individual post " + id;
    }

    @GetMapping("/create")
    @ResponseBody
    public String getCreatePage() {
        return "view the form for creating a post";
    }

    @PostMapping("/create")
    @ResponseBody
    public String submitPost() {
        return "create a new post";
    }
}
