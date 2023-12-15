package com.codeup.hippogriffspringblog.controllers;

import com.codeup.hippogriffspringblog.dao.AdDao;
import com.codeup.hippogriffspringblog.dao.CategoryDao;
import com.codeup.hippogriffspringblog.dao.UserRepository;
import com.codeup.hippogriffspringblog.models.Ad;
import com.codeup.hippogriffspringblog.models.Category;
import com.codeup.hippogriffspringblog.models.User;
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
    public String adIndex(Model model) {
        // test the custom dao function
//        User testUser = userDao.findUserByHisHerNumber(1L);
//        System.out.println(testUser.getUsername());

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
        model.addAttribute("ad", new Ad());
        model.addAttribute("categories", categoryDao.findAll());
        return "/ads/create";
    }

    @PostMapping({"/create", "/create/"})
    public String doCreate(@ModelAttribute Ad ad) {
        ad.setUser(userDao.findUserById(1L));
        adDao.save(ad);
        return "redirect:/ads";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable long id,
                               Model model) {
        Ad ad;
        if (adDao.findById(id).isPresent()) {
            ad = adDao.findById(id).get();
        } else {
            ad = null;
        }
        model.addAttribute("ad", ad);
        return "/ads/edit";
    }

    @PostMapping("/{id}/edit")
    public String editAd(@ModelAttribute Ad modifiedAd){
        // TODO: refactor this to check isPresent()
        // because that's the responsible thing to do
        Ad oldAd = adDao.findById(modifiedAd.getId()).get();

        modifiedAd.setUser(oldAd.getUser());
        adDao.save(modifiedAd);
        return "redirect:/ads";
    }
}
