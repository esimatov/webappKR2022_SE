package com.simatov.webapp.controllers;

import com.simatov.webapp.models.Rate;
import com.simatov.webapp.models.User;
import com.simatov.webapp.repo.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rates")
public class RateController {

    @Autowired
    private RateRepository rateRepository;

    @GetMapping("/")
    public String rateList(Model model) {
        model.addAttribute("title", "Computer Checker");
        Iterable<Rate> rates = rateRepository.findAll();
        model.addAttribute("rates", rates);
        return "rates";
    }

    @GetMapping("/add")
    public String rateAdd(Model model) {
        model.addAttribute("title", "Computer Checker");
        return "rates-add";
    }

    @PostMapping("/add")
    public String ratesAddForm(
            @RequestParam String name,
            @RequestParam String comment,
            @RequestParam double workingCompPrice,
            @RequestParam double gamingCompPrice,
            Model model) {
        Rate rate = new Rate(name, comment, workingCompPrice, gamingCompPrice);
        rateRepository.save(rate);
        return "redirect:/rates/";
    }

    @PostMapping("{id}/delete")
    public String deleteRate(@PathVariable (value = "id") Long id) {
        rateRepository.deleteById(id);
        return "redirect:/rates/";
    }
}
