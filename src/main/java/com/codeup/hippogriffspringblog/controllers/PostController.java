package com.codeup.hippogriffspringblog.controllers;

import com.codeup.hippogriffspringblog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {
    private Post post1 = new Post(1, "Post 1", "qewrqwerqwerqwe");
    private Post post2 = new Post(2, "Post 2", "qewrqwerqwerqwe");
    private Post post3 = new Post(3, "Post 3", "qewrqwerqwerqwe");

    // Arrays.asList(1, 2, 3)
    private List<Post> posts = new ArrayList<>(List.of(post1, post2, post3));


    @GetMapping({"/", ""})
    public String getAllPosts(Model model) {
        model.addAttribute("posts", posts);
        return "/posts/index";
    }

    @GetMapping("/{id}")
    public String getPostDetail(@PathVariable long id,
                                Model model) {
        model.addAttribute("post", post1);
        return "/posts/show";
    }

    @GetMapping("/create")
    public String getCreatePage() {
        return "/posts/create";
    }

    @PostMapping("/create")
    public String submitPost(@RequestParam(name = "title") String title,
                             @RequestParam(name = "body") String body) {
        Post post = new Post(99, title, body);
        posts.add(post);

        return "redirect:/posts";
    }
}
