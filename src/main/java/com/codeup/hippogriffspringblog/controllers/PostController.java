package com.codeup.hippogriffspringblog.controllers;

import com.codeup.hippogriffspringblog.dao.PostRepository;
import com.codeup.hippogriffspringblog.dao.UserRepository;
import com.codeup.hippogriffspringblog.models.Post;
import com.codeup.hippogriffspringblog.models.User;
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


    private final PostRepository postDao;
    private final UserRepository userDao;

    // Plain English translation:
    // Every time a PostController gets created
    // A postDao should be part of it
    public PostController(PostRepository postDao, UserRepository userDao){
        this.postDao = postDao;
        this.userDao = userDao;
    }

    @GetMapping({"/", ""})
    public String getAllPosts(Model model) {
        model.addAttribute("posts", postDao.findAll());
        return "/posts/index";
    }

    @GetMapping({"/{id}", "/{id}/"})
    public String getPostDetail(@PathVariable long id,
                                Model model) {
        Post post;
        if (postDao.findById(id).isPresent()){
            post = postDao.findById(id).get();
        } else {
            post = new Post("Post not found", "");
        }
        model.addAttribute("post", post);
        return "/posts/show";
    }

    @GetMapping("/create")
    public String getCreatePage() {
        return "/posts/create";
    }

    @PostMapping("/create")
    public String submitPost(@RequestParam(name = "title") String title,
                             @RequestParam(name = "body") String body) {
        Post post = new Post(title, body);
        User user = userDao.findUserById(1L);
        post.setUser(user);
        postDao.save(post);

        return "redirect:/posts";
    }
}
