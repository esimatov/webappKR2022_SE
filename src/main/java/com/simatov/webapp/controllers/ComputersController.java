package com.simatov.webapp.controllers;

import com.simatov.webapp.models.Computer;
import com.simatov.webapp.models.Game;
import com.simatov.webapp.models.Type;
import com.simatov.webapp.repo.ComputerRepository;
import com.simatov.webapp.repo.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/computers")
public class ComputersController {

    @Autowired
    private ComputerRepository computerRepository;

    @Autowired
    private GameRepository gameRepository;

    @GetMapping("/")
    public String compList(Model model) {
        model.addAttribute("title", "Computer Checker");
        Iterable<Computer> computers = computerRepository.findAll();
        model.addAttribute("computers", computers);
        return "computers";
    }

    @GetMapping("/add")
    public String compAdd(Model model) {
        model.addAttribute("title", "Computer Checker");
        model.addAttribute("types", Type.values());
        return "computers-add";
    }

    @PostMapping("/add")
    public String compAddForm(@RequestParam String name,
                              @RequestParam String description,
                              @RequestParam String type,
                              @RequestParam String comment,
                              Model model) {
        if (type.equals("working")) {
            Computer computer = new Computer(name, Type.WORKING, description, comment);
            computerRepository.save(computer);
        } else {
            Computer computer = new Computer(name, Type.GAMING, description, comment);
            computerRepository.save(computer);
        }

        return "redirect:/computers/";
    }

    @GetMapping("/{id}")
    public String compPage(@PathVariable(value = "id") Long id,
                           Model model) {
        model.addAttribute("title", "Computer Checker");
        Computer computer = computerRepository.findById(id).orElseThrow();
        model.addAttribute("comp", computer);
        List<Game> games = computer.getGames();
        model.addAttribute("games", games);
        return "computer-page";
    }


}
