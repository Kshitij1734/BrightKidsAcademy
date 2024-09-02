package com.brightkids_academy.controllers;

import com.brightkids_academy.entities.ImageAdmin;
import com.brightkids_academy.repos.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class NavigationController {

    @Autowired
    private ImageRepository imageRepo;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/alphabet_learning")
    public String alphabet_learning() {
        return "alphabet_learning";
    }
    
    @GetMapping("/alphaRecogGame")
    public String alphaRecogGame() {
        return "alphaRecogGame";
    }
    @GetMapping("/alphaAdmin")
    public String alphaAdmin() {
        return "alphaAdmin";
    }
    

    @GetMapping("/number")
    public String number(Model model) {
        List<ImageAdmin> nums = imageRepo.findAll();
        model.addAttribute("nums", nums);
        return "number";
    }

    @GetMapping("/numberAdmin")
    public String numberAdmin() {
        return "numberAdmin";
    }
    @GetMapping("/norecognition")
    public String norecognition() {
        return "norecognition";
    }
    
    
//    chirag code
    @GetMapping("/number_learning")
    public String number() {

        return "number";
    }
    
    @GetMapping("/story")
    public String story() {

        return "story";
    }
    @GetMapping("/admin")
    public String admin() {

        return "admin";
    }
    @GetMapping("/dashboard_admin")
    public String dashboard_admin() {

        return "dashboard_admin";
    }
    @GetMapping("/user_admin")
    public String user_admin() {

        return "user_admin";
    }
    @GetMapping("/storyAdmin")
    public String storyAdmin() {

        return "storyAdmin";
    }
    
  
    
    @GetMapping("/setting_admin")
    public String setting_admin() {

        return "setting_admin";
    }
    
   
}
