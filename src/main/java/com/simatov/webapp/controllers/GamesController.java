package com.simatov.webapp.controllers;

import com.simatov.webapp.models.Computer;
import com.simatov.webapp.models.Game;
import com.simatov.webapp.repo.ComputerRepository;
import com.simatov.webapp.repo.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/games")
public class GamesController {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    ComputerRepository computerRepository;

    @GetMapping("/")
    public String gameList(Model model) {
        Iterable<Game> games = gameRepository.findAll();
        model.addAttribute("games", games);
        return "games";
    }

    @GetMapping("/add")
    public String gameAdd(Model model) {
        Iterable<Computer> comps = computerRepository.findAll();
        model.addAttribute("comps", comps);
        model.addAttribute("title", "Computer Checker");
        return "games-add";
    }

    @PostMapping("/add")
    public String userAddForm(@RequestParam String name,
                              @RequestParam String description,
                              @RequestParam String url,
                              Model model, Computer computer) {
       Game game = new Game(name, description, url);
       gameRepository.save(game);
       return "redirect:/games/";
    }

    @GetMapping("/{id}")
    public String gamePage(@PathVariable(value = "id") Long id,
                           Model model) {
        Game game = gameRepository.findById(id).orElseThrow();
        model.addAttribute("game", game);
        Iterable<Computer> comps = computerRepository.findAll();
        model.addAttribute("comps", comps);
        return "game-page";
    }

    @PostMapping("/{id}/setup")
    public String setupGame( @PathVariable(value = "id") Long id,
                             @RequestParam Long compId,
                             Model model) {
        Computer comp = computerRepository.findById(compId).orElseThrow();
        Game game = gameRepository.findById(id).orElseThrow();
        comp.addGame(game);
        gameRepository.save(game);
        computerRepository.save(comp);
        model.addAttribute("id", id);
        return "redirect:/games/";
    }

    @PostMapping("/{id}/delete")
    public String deleteGame( @PathVariable(value = "id") Long id,
                             @RequestParam Long compId,
                              Model model) {
        Computer comp = computerRepository.findById(compId).orElseThrow();
        Game game = gameRepository.findById(id).orElseThrow();
        comp.removeGame(game);
        gameRepository.save(game);
        computerRepository.save(comp);
        model.addAttribute("id", id);
        return "redirect:/games/";
    }

}
