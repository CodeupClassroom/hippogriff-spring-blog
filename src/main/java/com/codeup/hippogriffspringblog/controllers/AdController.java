package com.codeup.hippogriffspringblog.controllers;

import com.codeup.hippogriffspringblog.dao.AdDao;
import com.codeup.hippogriffspringblog.dao.CategoryDao;
import com.codeup.hippogriffspringblog.dao.UserRepository;
import com.codeup.hippogriffspringblog.models.Ad;
import com.codeup.hippogriffspringblog.models.Category;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Controller
@RequestMapping("/ads")
public class AdController {

    private AdDao adDao;
    private UserRepository userDao;
    private CategoryDao categoryDao;

//    public AdController(AdDao adDao){
//        this.adDao = adDao;
//    }

    @GetMapping(value = {"", "/"})
    public String adIndex(Model model){
        List<Ad> ads = adDao.findAll();
        model.addAttribute("ads", ads);
        return "/ads/index";
    }

    @GetMapping({"/{id}", "/{id}/"})
    public String showAd(@PathVariable long id,
                         Model model) {
        Ad ad;
        if (adDao.findById(id).isPresent()){
            ad = adDao.findById(id).get();
        } else {
            ad = new Ad("Ad not found", "");
        }
        model.addAttribute("ad", ad);
        return "/ads/show";
    }

    @GetMapping({"/create", "/create/"})
    public String showCreate(Model model) {
        model.addAttribute("categories", categoryDao.findAll());
        return "/ads/create";
    }

    @PostMapping({"/create", "/create/"})
    public String doCreate(@RequestParam(name="title") String title,
                           @RequestParam(name = "description") String description, @RequestParam(name= "categories") List<String> categories) {
        Set<Category> adCategories = new HashSet<>();
        for (String category : categories){
            adCategories.add(categoryDao.findCategoryByName(category));
        }
        Ad ad = new Ad(title, description);
        ad.setUser(userDao.findUserById(1L));
        ad.setCategories(adCategories);
        adDao.save(ad);
        return "redirect:/ads";
    }
}
