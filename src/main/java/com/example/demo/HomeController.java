package com.example.demo;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.time.LocalDateTime;


@Controller
public class HomeController {
    @Autowired
    LisTingsRepository lisTingsRepository;

    @RequestMapping("/")
    public String homePage() {
        return "HomePage";
    }

    @GetMapping("/addlis")
    public String movieForm(Model model) {
        model.addAttribute("lis", new LisTings());
        return "form";
    }

    @GetMapping("/list")
    public String listMovies(Model model) {
        model.addAttribute("lis", lisTingsRepository.findAllByOrderByDateDesc());
        return "list";
    }

    @PostMapping("/process")
    public String processForm(@Valid @ModelAttribute("lis")LisTings liss, BindingResult result) {
        if (result.hasErrors()) {
            return "form";
        }
        liss.setDate(LocalDateTime.now());
        lisTingsRepository.save(liss);
        return "redirect:/list";
    }

//    @RequestMapping("/detail/{id}")
//    public String showLis(@PathVariable("id") long id, Model model) {
//        model.addAttribute("lis", lisTingsRepository.findById(id).get());
//        return "show";
//    }
//
//    @RequestMapping("/update/{id}")
//    public String updateLis(@PathVariable("id") long id, Model model) {
//        model.addAttribute("lis", lisTingsRepository.findById(id).get());
//        return "movieform";
//    }
//
//    @RequestMapping("/delete/{id}")
//    public String delLis(@PathVariable("id") long id) {
//        lisTingsRepository.deleteById(id);
//        return "redirect:/";
//    }
}