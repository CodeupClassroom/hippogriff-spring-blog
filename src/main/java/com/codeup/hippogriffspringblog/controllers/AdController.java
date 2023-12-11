package com.codeup.hippogriffspringblog.controllers;

import com.codeup.hippogriffspringblog.models.Ad;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/ads")
public class AdController {

    Ad ad = new Ad("Amazing new product!", "The everything thing will solve all your problems! It will clean your floors, wash your dishes, cook your dinner, and walk your dog!");
    Ad ad2 = new Ad("Latest flying car", "This new flying car will take off above traffic and save you time on your morning commute! No pilot license required!");
    Ad ad3 = new Ad("Sunshine in a bottle", "Captured sunlight in a convenient recyclable bottle for all your natural lighting needs!");
    List<Ad> ads = new ArrayList<>(List.of(ad, ad2, ad3));

    @GetMapping("")
    public String adIndex(Model model){
        model.addAttribute("ads", ads);
        return "/ads/index";
    }

    @GetMapping("/{id}")
    public String showAd(@PathVariable long id,
                         Model model) {
        model.addAttribute("ad", ad2);
        return "/ads/show";
    }

    @GetMapping("/create")
    public String showCreate() {
        return "/ads/create";
    }

    @PostMapping("/create")
    public String doCreate(@RequestParam(name="title") String title,
                           @RequestParam(name = "description") String description) {
        Ad ad = new Ad(title, description);
        ads.add(ad);
        return "redirect:/ads";
    }
}
