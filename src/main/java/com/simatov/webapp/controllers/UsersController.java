package com.simatov.webapp.controllers;

import com.simatov.webapp.models.Rate;
import com.simatov.webapp.models.User;
import com.simatov.webapp.repo.RateRepository;
import com.simatov.webapp.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RateRepository rateRepository;

    @GetMapping("/")
    public String userList(Model model) {
        model.addAttribute("title", "Computer Checker");
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/add")
    public String userAdd(Model model) {
        model.addAttribute("title", "Computer Checker");
        return "users-add";
    }

    @PostMapping("/add")
    public String userAddForm(@RequestParam String name,
                              @RequestParam String tel,
                              @RequestParam Double balance,
                              Model model) {
        User user = new User(name, tel, balance, 0, 0);
        userRepository.save(user);
        return "redirect:/users/";
    }

    @GetMapping("/{id}")
    public String userPage(Model model, @PathVariable(value = "id") Long id) {
        if (!userRepository.existsById(id)) {
            return "redirect:/users/";
        }
        Optional<User> user = userRepository.findById(id);
        ArrayList<User> res = new ArrayList<>();
        user.ifPresent(res::add);
        model.addAttribute("user", res);
        Iterable<Rate> rates = rateRepository.findAll();
        model.addAttribute("rates", rates);
        return "user-page";
    }

    @PostMapping("/{id}")
    public String userPageForm( @PathVariable(value = "id") Long id,
                                @RequestParam double balance,
                                @RequestParam int workingCompTime,
                                @RequestParam int gamingCompTime,
                                @RequestParam Long rateId,
                              Model model) {
        User user = userRepository.findById(id).orElseThrow();
        Rate rate = rateRepository.findById(rateId).orElseThrow();
        user.setBalance(user.getBalance() + balance);
        if (workingCompTime!=0) {
            user.setBalance(user.getBalance() - workingCompTime*rate.getWorkingCompPrice()/60);
            user.setWorkingCompTime(user.getWorkingCompTime() + workingCompTime);
        }
        if (gamingCompTime!=0) {
            user.setBalance(user.getBalance() - gamingCompTime*rate.getGamingCompPrice()/60);
            user.setGamingCompTime(user.getGamingCompTime() + gamingCompTime);
        }
        userRepository.save(user);
        return "redirect:/users/";
    }

    @PostMapping("/{id}/editUser")
    public String userEditForm (@PathVariable(value = "id") Long id,
                                @RequestParam String name,
                                @RequestParam String tel,
                                Model model) {
        User user = userRepository.findById(id).orElseThrow();
        model.addAttribute("user", user);
        user.setName(name);
        user.setTel(tel);
        userRepository.save(user);
        return "redirect:/users/";
    }

}
